package luongld.freeswitch.xml.sections.configuration.accesscontrol;

import org.apache.commons.lang3.StringUtils;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "node")
@XmlAccessorType(XmlAccessType.NONE)
public class NetworkNode {

    @XmlAttribute(name = "type")
    private String type;

    @XmlAttribute(name = "cidr")
    private String cidr;

    @XmlAttribute(name = "domain")
    private String domain;

    @XmlAttribute(name = "description")
    private String description;

    private NetworkNode() {
    }

    public NetworkNode(String cidr, String domain, boolean allow, String description) {
        this();

        assert StringUtils.isNotEmpty(cidr) || StringUtils.isNotEmpty(domain);
        this.cidr = cidr;
        this.domain = domain;
        this.description = description;

        this.type = allow ? "allow" : "deny";
    }

    public NetworkNode(String cidr, String domain, boolean allow) {
        this(cidr, domain, allow, null);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCidr() {
        return cidr;
    }

    public void setCidr(String cidr) {
        this.cidr = cidr;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
