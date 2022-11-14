package luongld.freeswitch.configurations.accesscontrol.commands.handlers;

import luongld.cqrs.RequestHandler;
import luongld.freeswitch.configurations.accesscontrol.AccessControl;
import luongld.freeswitch.configurations.accesscontrol.commands.AllowAccessControlCommand;
import luongld.freeswitch.configurations.accesscontrol.repositories.AccessControlRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Component
public class AllowAccessControlHandler implements RequestHandler<AccessControl, AllowAccessControlCommand> {

    private final AccessControlRepository accessControlRepository;

    public AllowAccessControlHandler(AccessControlRepository accessControlRepository) {
        this.accessControlRepository = accessControlRepository;
    }

    @Override
    @Transactional
    public AccessControl handle(AllowAccessControlCommand cmd) {
        var accessControl = accessControlRepository
                .findById(cmd.getId())
                .orElseThrow(() -> new EntityNotFoundException("AccessControl[" + cmd.getId() + "]"));

        accessControl.addDetail(cmd.getCidr(), cmd.getDomain(), true, cmd.getDescription());

        return accessControlRepository.save(accessControl);
    }
}
