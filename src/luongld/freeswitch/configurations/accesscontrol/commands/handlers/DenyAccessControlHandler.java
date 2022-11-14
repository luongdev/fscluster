package luongld.freeswitch.configurations.accesscontrol.commands.handlers;

import luongld.cqrs.RequestHandler;
import luongld.freeswitch.configurations.accesscontrol.AccessControl;
import luongld.freeswitch.configurations.accesscontrol.commands.DenyAccessControlCommand;
import luongld.freeswitch.configurations.accesscontrol.repositories.AccessControlRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Component
public class DenyAccessControlHandler implements RequestHandler<AccessControl, DenyAccessControlCommand> {

    private final AccessControlRepository accessControlRepository;

    public DenyAccessControlHandler(AccessControlRepository accessControlRepository) {
        this.accessControlRepository = accessControlRepository;
    }

    @Override
    @Transactional
    public AccessControl handle(DenyAccessControlCommand cmd) {
        var accessControl = accessControlRepository
                .findById(cmd.getId())
                .orElseThrow(() -> new EntityNotFoundException("AccessControl[" + cmd.getId() + "]"));

        accessControl.addDetail(cmd.getCidr(), cmd.getDomain(), false, cmd.getDescription());

        return accessControlRepository.save(accessControl);
    }
}
