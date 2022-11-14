package luongld.freeswitch;

import luongld.cqrs.Bus;
import luongld.freeswitch.xml.sections.ConfigurationSection;
import luongld.freeswitch.xml.Document;
import luongld.freeswitch.xml.sections.configuration.accesscontrol.AccessControlConfiguration;
import luongld.freeswitch.xml.sections.configuration.accesscontrol.NetworkContainer;
import luongld.freeswitch.xml.sections.configuration.accesscontrol.NetworkList;
import luongld.freeswitch.xml.sections.configuration.accesscontrol.NetworkNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.util.ArrayList;
import java.util.HashSet;

@SpringBootApplication
public class FClusterApplication implements CommandLineRunner {

    private static ConfigurableApplicationContext context;

    private static final Class<FClusterApplication> clazz = FClusterApplication.class;


    public static void main(String[] args) {
        FClusterApplication.context = SpringApplication.run(clazz, args);
    }


    @Autowired
    Bus bus;

    @Override
    public void run(String... args) throws Exception {

        var networkContainer = new NetworkContainer(new ArrayList<>() {

            {
                add(new NetworkList("event_socket", false, new ArrayList<>() {
                    {
                        add(new NetworkNode("127.0.0.1/32", null, true));
                        add(new NetworkNode("10.196.24.42/32", null, true));
                        add(new NetworkNode("172.16.86.0/24", null, true));
                    }
                }));
                add(new NetworkList("domains", false, new ArrayList<>() {
                    {
                        add(new NetworkNode(null, "voice.metechvn.com", true));
                    }
                }));
            }

        });

        var sections = new HashSet<ConfigurationSection>();
        sections.add(new ConfigurationSection(new AccessControlConfiguration(networkContainer)));

        var document = new Document(sections);

        try {
            var jaxbContext = JAXBContext.newInstance(Document.class);
            var jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            jaxbMarshaller.marshal(document, System.out);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
