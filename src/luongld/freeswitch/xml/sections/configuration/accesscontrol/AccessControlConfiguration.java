package luongld.freeswitch.xml.sections.configuration.accesscontrol;

import luongld.freeswitch.xml.sections.ConfigurationSection;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "configuration")
@XmlAccessorType(XmlAccessType.NONE)
public class AccessControlConfiguration extends ConfigurationSection.Configuration {

    @XmlElementRef(name = "network-lists")
    private NetworkContainer networkContainer;

    public static String EMPTY_CONFIGURATION = """
            <?xml version="1.0" encoding="UTF-8" standalone="no"?>
            <document type="freeswitch/xml">
                <section name="configuration">
                    <configuration name="acl.conf" description="Network Lists">
                        <network-lists />
                    </configuration>
                </section>
            </document>
            """;

    public AccessControlConfiguration() {
        super("acl.conf", "Access controls");
    }

    public AccessControlConfiguration(NetworkContainer networkContainer) {
        this();
        this.networkContainer = networkContainer;
    }

    public NetworkContainer getNetworkContainer() {
        return networkContainer;
    }

    public void setNetworkContainer(NetworkContainer networkContainer) {
        this.networkContainer = networkContainer;
    }
}
