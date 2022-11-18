package luongld.freeswitch.esl.cli;

import luongld.freeswitch.esl.InboundClient;
import luongld.freeswitch.esl.transport.message.EslMessage;

import java.util.List;

public interface Cli {

    List<EslMessage> apply(InboundClient inboundClient);

}
