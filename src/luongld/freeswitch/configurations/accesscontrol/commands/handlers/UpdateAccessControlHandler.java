package luongld.freeswitch.configurations.accesscontrol.commands.handlers;

import luongld.cqrs.RequestHandler;
import luongld.freeswitch.configurations.accesscontrol.AccessControl;
import luongld.freeswitch.configurations.accesscontrol.commands.UpdateAccessControlCommand;
import luongld.freeswitch.configurations.accesscontrol.repositories.AccessControlRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

@Component
public class UpdateAccessControlHandler implements RequestHandler<AccessControl, UpdateAccessControlCommand> {

    private final AccessControlRepository accessControlRepository;

    public UpdateAccessControlHandler(AccessControlRepository accessControlRepository) {
        this.accessControlRepository = accessControlRepository;
    }

    @Override
    public AccessControl handle(UpdateAccessControlCommand cmd) {
        var accessControl = accessControlRepository
                .findById(cmd.getId())
                .orElseThrow(() -> new EntityNotFoundException("AccessControl[" + cmd.getId() + "]"));

        accessControl.setName(cmd.getName());
        accessControl.setDescription(cmd.getDescription());

        return accessControlRepository.save(accessControl);
    }

}
