package luongld.freeswitch.configurations.accesscontrol.commands;

import luongld.cqrs.Request;
import luongld.freeswitch.configurations.accesscontrol.AccessControl;
import org.apache.commons.lang3.StringUtils;

public class CreateAccessControlCommand implements Request<AccessControl> {

    private String name;
    private boolean allow;
    private String description;

    private CreateAccessControlCommand() {
        this.allow = false;
    }

    public CreateAccessControlCommand(String name, boolean allow, String description) {
        this();
        assert StringUtils.isNotEmpty(name);

        this.name = name;
        this.allow = allow;
        this.description = description;
    }

    public CreateAccessControlCommand(String name, boolean allow) {
        this(name, allow, null);
    }

    public CreateAccessControlCommand(String name, String description) {
        this(name, false, description);
    }

    public String getName() {
        return name;
    }

    public boolean isAllow() {
        return allow;
    }

    public String getDescription() {
        return description;
    }
}
