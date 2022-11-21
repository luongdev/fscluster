package luongld.freeswitch.domain.commands;

import luongld.cqrs.Request;
import luongld.freeswitch.domain.Domain;
import org.apache.commons.lang3.StringUtils;

public class CreateDomainCommand implements Request<Domain> {

    private String domainName;

    private CreateDomainCommand() {
    }

    public CreateDomainCommand(String domainName) {
        this();
        assert StringUtils.isNotEmpty(domainName);

        this.domainName = domainName;
    }

    public String getDomainName() {
        return domainName;
    }
}
