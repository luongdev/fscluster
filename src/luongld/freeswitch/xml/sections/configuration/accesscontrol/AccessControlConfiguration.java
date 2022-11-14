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
