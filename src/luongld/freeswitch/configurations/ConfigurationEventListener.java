package luongld.freeswitch.configurations;

import luongld.cqrs.Bus;
import luongld.freeswitch.configurations.accesscontrol.commands.GenerateAccessControlXmlCommand;
import luongld.freeswitch.esl.IEslEventListener;
import luongld.freeswitch.esl.transport.event.EslEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ConfigurationEventListener implements IEslEventListener {

    private final Bus bus;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public ConfigurationEventListener(Bus bus) {
        this.bus = bus;
    }

    @Override
    public void eventReceived(String addr, EslEvent event) {
        if (event.getSubclass() == null || !"mepbx::config".equalsIgnoreCase(event.getSubclass())) return;

        var xml = bus.execute(new GenerateAccessControlXmlCommand());
        System.out.println(xml);
    }

    @Override
    public void backgroundJobResultReceived(String addr, EslEvent event) {

    }
}
