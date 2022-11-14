package luongld.freeswitch.configurations.accesscontrol.commands;

import luongld.cqrs.Request;

import java.util.UUID;

public class GenerateAccessControlXmlCommand implements Request<String> {

    private UUID id;

    private GenerateAccessControlXmlCommand() {}

    public GenerateAccessControlXmlCommand(UUID id) {
        this();
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}
