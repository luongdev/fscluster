package luongld.freeswitch.configurations.accesscontrol.commands.handlers;

import luongld.cqrs.RequestHandler;
import luongld.freeswitch.configurations.accesscontrol.AccessControl;
import luongld.freeswitch.configurations.accesscontrol.AccessControlDetail;
import luongld.freeswitch.configurations.accesscontrol.commands.GenerateAccessControlXmlCommand;
import luongld.freeswitch.configurations.accesscontrol.repositories.AccessControlRepository;
import luongld.freeswitch.xml.Document;
import luongld.freeswitch.xml.sections.configuration.accesscontrol.AccessControlConfiguration;
import luongld.freeswitch.xml.sections.configuration.accesscontrol.NetworkContainer;
import luongld.freeswitch.xml.sections.configuration.accesscontrol.NetworkList;
import luongld.freeswitch.xml.sections.configuration.accesscontrol.NetworkNode;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

@Component
public class GenerateAccessControlXmlHandler implements RequestHandler<String, GenerateAccessControlXmlCommand> {

    private final Marshaller marshaller;
    private final AccessControlRepository accessControlRepository;

    public GenerateAccessControlXmlHandler(AccessControlRepository accessControlRepository) throws JAXBException {
        this.accessControlRepository = accessControlRepository;

        var jaxbContext = JAXBContext.newInstance(Document.class);
        this.marshaller = jaxbContext.createMarshaller();
        this.marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
    }

    @Override
    @Transactional
    public String handle(GenerateAccessControlXmlCommand cmd) {
        var networks = new ArrayList<NetworkList>();

        var accessControls = accessControlRepository.findAll();
        for (var accessControl : accessControls) {
            var network = networkFrom(accessControl);
            if (network == null) continue;

            networks.add(network);
        }


        return marshal(networks);
    }

    private NetworkList networkFrom(AccessControl accessControl) {
        if (accessControl == null) return null;

        var accessControlDetails = accessControl.getAccessControlDetails() == null
                ? new HashSet<AccessControlDetail>()
                : accessControl.getAccessControlDetails().values();
        var nodes = accessControlDetails
                .stream()
                .map(v -> new NetworkNode(v.getCidr(), v.getDomain(), v.isAllow(), v.getDescription()))
                .toList();

        return new NetworkList(accessControl.getName(), accessControl.isAllow(), nodes);
    }

    private String marshal(List<NetworkList> networks) {
        if (networks == null) networks = Collections.emptyList();

        var accessControlConfiguration = new AccessControlConfiguration(new NetworkContainer(networks));
        try (var os = new ByteArrayOutputStream()) {
            this.marshaller.marshal(accessControlConfiguration, os);

            return os.toString();
        } catch (IOException | JAXBException ignored) {
            return AccessControlConfiguration.EMPTY_CONFIGURATION;
        }
    }
}
