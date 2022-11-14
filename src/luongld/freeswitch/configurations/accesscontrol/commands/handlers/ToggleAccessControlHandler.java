package luongld.freeswitch.configurations.accesscontrol.commands.handlers;

import luongld.cqrs.RequestHandler;
import luongld.freeswitch.configurations.accesscontrol.AccessControl;
import luongld.freeswitch.configurations.accesscontrol.commands.ToggleAccessControlCommand;
import luongld.freeswitch.configurations.accesscontrol.repositories.AccessControlRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Component
public class ToggleAccessControlHandler implements RequestHandler<AccessControl, ToggleAccessControlCommand> {

    private final AccessControlRepository accessControlRepository;

    public ToggleAccessControlHandler(AccessControlRepository accessControlRepository) {
        this.accessControlRepository = accessControlRepository;
    }

    @Override
    @Transactional
    public AccessControl handle(ToggleAccessControlCommand cmd) {
        var accessControl = accessControlRepository.findIncludeDetails(cmd.getId());
        if (accessControl == null) throw new EntityNotFoundException("AccessControl[" + cmd.getId() + "]");

        accessControl.setAllow(!accessControl.isAllow());
        accessControl.getAccessControlDetails().forEach((k, v) -> v.setAllow(!v.isAllow()));

        return accessControlRepository.save(accessControl);
    }
}
