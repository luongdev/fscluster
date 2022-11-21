package luongld.freeswitch.xml.sections.configuration.accesscontrol;

import org.apache.commons.lang3.StringUtils;

import javax.xml.bind.annotation.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@XmlRootElement(name = "list")
@XmlAccessorType(XmlAccessType.NONE)
public class NetworkList {

    @XmlAttribute(name = "name")
    private String name;

    @XmlAttribute(name = "default")
    private String _default;

    @XmlElementRef(name = "node")
    private Set<NetworkNode> nodes;

    private NetworkList() {
        this._default = "deny";
        this.nodes = new HashSet<>();
    }

    public NetworkList(String name, boolean allow) {
        this(name, allow, null);
    }

    public NetworkList(String name, boolean allow, Collection<NetworkNode> nodes) {
        this();

        assert StringUtils.isNotEmpty(name);
        this.name = name;
        this._default = allow ? "allow" : "deny";

        if (nodes == null || nodes.isEmpty()) return;

        this.nodes.addAll(new HashSet<>(nodes));
    }

    public NetworkList node(String cidr, String domainName, boolean allow) {
        this.nodes.add(new NetworkNode(cidr, domainName, allow));

        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDefault() {
        return _default;
    }

    public void setDefault(String _default) {
        this._default = _default;
    }

    public Set<NetworkNode> getNodes() {
        return nodes;
    }

    public void setNodes(Set<NetworkNode> nodes) {
        this.nodes = nodes;
    }
}
