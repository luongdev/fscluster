package luongld.freeswitch.domain;

import org.apache.commons.lang3.StringUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "domains")
public class Domain {

    @Id
    @Column(name = "domain_name")
    private String domainName;

    @Column(name = "is_enabled", nullable = false)
    private boolean enabled;

    private Domain() {
        this.enabled = true;
    }

    public Domain(String domainName) {
        this();
        assert StringUtils.isNotEmpty(domainName);

        this.domainName = domainName;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
