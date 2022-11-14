package luongld.freeswitch.configurations.accesscontrol;

import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(
        name = "access_control_details",
        indexes = @Index(name = "ac_unique_address", columnList = "cidr,domain,access_control_id", unique = true)
)
public class AccessControlDetail {

    @Id
    private UUID id;

    @Column(name = "cidr", length = 20, nullable = false)
    private String cidr;

    @Column(name = "domain", nullable = false)
    private String domain;

    @Column(name = "is_allow", nullable = false)
    private boolean allow = true;

    @Column(name = "description", length = 510)
    private String description;

    private transient boolean isNew;

    @ManyToOne
    @JoinColumn(name = "access_control_id")
    private AccessControl accessControl;

    public AccessControlDetail() {
        this.id = UUID.randomUUID();
    }

    public AccessControlDetail(String cidr, String domain, boolean allow) {
        this(cidr, domain, allow, null);
    }

    public AccessControlDetail(String cidr, String domain, boolean allow, String description) {
        this();
        assert StringUtils.isNotEmpty(cidr) || StringUtils.isNotEmpty(domain);

        this.allow = allow;
        this.cidr = StringUtils.isNotEmpty(cidr) ? cidr : "";
        this.domain = StringUtils.isNotEmpty(domain) ? domain : "";
        this.description = description;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public boolean isAllow() {
        return allow;
    }

    public void setAllow(boolean allow) {
        this.allow = allow;
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

    public AccessControl getAccessControl() {
        return accessControl;
    }

    public void setAccessControl(AccessControl accessControl) {
        this.accessControl = accessControl;
    }
}
