package luongld.freeswitch.configurations.accesscontrol.commands.handlers;

import luongld.cqrs.RequestHandler;
import luongld.freeswitch.configurations.accesscontrol.commands.GenerateAccessControlXmlCommand;
import luongld.freeswitch.configurations.accesscontrol.repositories.AccessControlRepository;
import luongld.freeswitch.xml.Document;
import luongld.freeswitch.xml.sections.configuration.accesscontrol.AccessControlConfiguration;
import luongld.freeswitch.xml.sections.configuration.accesscontrol.NetworkContainer;
import luongld.freeswitch.xml.sections.configuration.accesscontrol.NetworkList;
import luongld.freeswitch.xml.sections.configuration.accesscontrol.NetworkNode;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

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
        var accessControls = accessControlRepository.findAll();
        var networks = new ArrayList<NetworkList>();
        for (var accessControl : accessControls) {
            var nodes = accessControl
                    .getAccessControlDetails()
                    .values()
                    .stream()
                    .map(v -> new NetworkNode(
                            v.getCidr(),
                            v.getDomain(),
                            v.isAllow(),
                            v.getDescription()
                    ))
                    .collect(Collectors.toList());
            networks.add(new NetworkList(accessControl.getName(), accessControl.isAllow(), nodes));
        }

        var accessControlConfiguration = new AccessControlConfiguration(new NetworkContainer(networks));
        try (var os = new ByteArrayOutputStream()) {
            this.marshaller.marshal(accessControlConfiguration, os);

            return os.toString();
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }

        return null;
    }
}
