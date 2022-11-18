package luongld.freeswitch.esl.cli;

import luongld.freeswitch.esl.InboundClient;
import luongld.freeswitch.esl.transport.message.EslMessage;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class Lua implements Cli {

    private final String[] args;

    public static final String COMMAND = "lua";

    public Lua(String file, String... args) {
        assert StringUtils.isNotEmpty(file);

        if (!file.endsWith(".lua")) file = String.format("%s.lua", file);

        this.args = ArrayUtils.addAll(new String[]{file}, args);
    }

    @Override
    public List<EslMessage> apply(InboundClient inboundClient) {
        return inboundClient.sendSyncApiCommand(Lua.COMMAND, args);
    }
}
