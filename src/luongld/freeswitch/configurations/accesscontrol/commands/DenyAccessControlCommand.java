package luongld.freeswitch.configurations.accesscontrol.commands;

import luongld.cqrs.Request;
import luongld.freeswitch.configurations.accesscontrol.AccessControl;
import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

public class DenyAccessControlCommand implements Request<AccessControl> {

    private UUID id;
    private String cidr;
    private String domain;
    private String description;

    private DenyAccessControlCommand() {
    }

    public DenyAccessControlCommand(UUID id, String cidr, String domain, String description) {
        assert id != null;
        assert StringUtils.isNotEmpty(cidr) || StringUtils.isNotEmpty(domain);
        this.id = id;
        this.cidr = cidr;
        this.domain = domain;
        this.description = description;
    }

    public DenyAccessControlCommand(UUID id, String cidr, String domain) {
        this(id, cidr, domain, null);
    }

    public UUID getId() {
        return id;
    }

    public String getCidr() {
        return cidr;
    }

    public String getDomain() {
        return domain;
    }

    public String getDescription() {
        return description;
    }

}
