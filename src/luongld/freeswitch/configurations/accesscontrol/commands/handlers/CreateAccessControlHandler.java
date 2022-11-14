package luongld.freeswitch.configurations.accesscontrol.commands.handlers;

import luongld.cqrs.RequestHandler;
import luongld.freeswitch.configurations.accesscontrol.AccessControl;
import luongld.freeswitch.configurations.accesscontrol.commands.CreateAccessControlCommand;
import luongld.freeswitch.configurations.accesscontrol.repositories.AccessControlRepository;
import org.springframework.stereotype.Component;

@Component
public class CreateAccessControlHandler implements RequestHandler<AccessControl, CreateAccessControlCommand> {

    private final AccessControlRepository accessControlRepository;

    public CreateAccessControlHandler(AccessControlRepository accessControlRepository) {
        this.accessControlRepository = accessControlRepository;
    }

    @Override
    public AccessControl handle(CreateAccessControlCommand cmd) {
        var accessControl = new AccessControl(cmd.getName(), cmd.isAllow(), cmd.getDescription());
        accessControlRepository.save(accessControl);

        return accessControl;
    }

}
