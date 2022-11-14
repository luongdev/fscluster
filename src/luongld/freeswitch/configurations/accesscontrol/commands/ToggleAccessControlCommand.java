package luongld.freeswitch.configurations.accesscontrol.commands;

import luongld.cqrs.Request;
import luongld.freeswitch.configurations.accesscontrol.AccessControl;

import java.util.UUID;

public class ToggleAccessControlCommand implements Request<AccessControl> {

    private UUID id;

    private ToggleAccessControlCommand() {
    }

    public ToggleAccessControlCommand(UUID id) {
        assert id != null;
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}
