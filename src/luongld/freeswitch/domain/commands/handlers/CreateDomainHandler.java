package luongld.freeswitch.domain.commands.handlers;

import luongld.cqrs.RequestHandler;
import luongld.freeswitch.domain.Domain;
import luongld.freeswitch.domain.commands.CreateDomainCommand;
import luongld.freeswitch.domain.exceptions.DomainDuplicateException;
import luongld.freeswitch.domain.repositories.DomainJpaRepository;
import org.springframework.stereotype.Component;

@Component
public class CreateDomainHandler implements RequestHandler<Domain, CreateDomainCommand> {

    private final DomainJpaRepository domainJpaRepository;

    public CreateDomainHandler(DomainJpaRepository domainJpaRepository) {
        this.domainJpaRepository = domainJpaRepository;
    }

    @Override
    public Domain handle(CreateDomainCommand cmd) {
        var domainOpt = domainJpaRepository.findById(cmd.getDomainName());
        if (domainOpt.isPresent()) throw new DomainDuplicateException(cmd.getDomainName());

        var domain = new Domain(cmd.getDomainName());

        return domainJpaRepository.save(domain);
    }
}
