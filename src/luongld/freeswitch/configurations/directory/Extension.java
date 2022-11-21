package luongld.freeswitch.configurations.directory;

import luongld.freeswitch.domain.Domain;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;

@Entity
@Table(name = "extensions")
public class Extension {

    @Id
    @Column(name = "identifier")
    private String identifier;

    @Column(name = "extension", length = 8, nullable = false)
    private String extension;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "outbound_caller_number", length = 20)
    private String outboundCallerNumber;

    @Column(name = "limit_max")
    private Integer limitMax;

    @Column(name = "limit_destination")
    private String limitDestination;

    @Column(name = "call_timeout", nullable = false)
    private int callTimeout = 30;

    @Column(name = "record")
    private String record;

    @Column(name = "is_enabled", nullable = false)
    private boolean enabled;

    @Column(name = "description")
    private String description;

    @Column(name = "domain_name", insertable = false, updatable = false)
    private String domainName;

    @ManyToOne
    @JoinColumn(name = "domain_name")
    private Domain domain;

    private Extension() {
        this.enabled = true;
    }

    public Extension(String extension, Domain domain) {
        this();

        assert StringUtils.isNotEmpty(extension);
        assert domain != null;

        this.extension = extension;
        this.domain = domain;
        this.identifier = String.format("%s@%s", extension, domain.getDomainName());
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOutboundCallerNumber() {
        return outboundCallerNumber;
    }

    public void setOutboundCallerNumber(String outboundCallerNumber) {
        this.outboundCallerNumber = outboundCallerNumber;
    }

    public Integer getLimitMax() {
        return limitMax;
    }

    public void setLimitMax(Integer limitMax) {
        this.limitMax = limitMax;
    }

    public String getLimitDestination() {
        return limitDestination;
    }

    public void setLimitDestination(String limitDestination) {
        this.limitDestination = limitDestination;
    }

    public int getCallTimeout() {
        return callTimeout;
    }

    public void setCallTimeout(int callTimeout) {
        this.callTimeout = callTimeout;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public Domain getDomain() {
        return domain;
    }

    public void setDomain(Domain domain) {
        this.domain = domain;
    }
}
