package luongld.freeswitch.configurations.accesscontrol.commands;

import luongld.cqrs.Request;
import luongld.freeswitch.configurations.accesscontrol.AccessControl;
import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

public class UpdateAccessControlCommand implements Request<AccessControl> {

    private UUID id;
    private String name;
    private String description;

    private UpdateAccessControlCommand() {
    }

    public UpdateAccessControlCommand(UUID id, String name, String description) {
        assert id != null;
        assert StringUtils.isNotEmpty(name);

        this.id = id;
        this.name = name;
        this.description = description;
    }

    public UpdateAccessControlCommand(UUID id, String name) {
        this(id, name, null);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
