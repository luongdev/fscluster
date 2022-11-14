package luongld.freeswitch.xml.sections.configuration.accesscontrol;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@XmlRootElement(name = "network-lists")
@XmlAccessorType(XmlAccessType.NONE)
public class NetworkContainer {

    @XmlElementRef(name = "list")
    private Set<NetworkList> networks;

    private NetworkContainer() {
        this.networks = new HashSet<>();
    }

    public NetworkContainer(Collection<NetworkList> networks) {
        this();

        if (networks == null || networks.isEmpty()) return;

        this.networks.addAll(new HashSet<>(networks));
    }

    public Set<NetworkList> getNetworks() {
        return networks;
    }

    public void setNetworks(Set<NetworkList> networks) {
        this.networks = networks;
    }
}
