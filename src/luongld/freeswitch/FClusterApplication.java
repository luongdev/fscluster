package luongld.freeswitch;

import luongld.cqrs.Bus;
import luongld.freeswitch.configurations.directory.commands.CreateExtensionCommand;
import luongld.freeswitch.domain.commands.CreateDomainCommand;
import luongld.freeswitch.esl.InboundClient;
import luongld.freeswitch.esl.cli.CliExecutor;
import luongld.freeswitch.esl.cli.Lua;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class FClusterApplication implements CommandLineRunner {

    private static ConfigurableApplicationContext context;

    private static final Class<FClusterApplication> clazz = FClusterApplication.class;


    public static void main(String[] args) {
        FClusterApplication.context = SpringApplication.run(clazz, args);
    }


    @Autowired
    Bus bus;

    @Autowired
    CacheManager cacheManager;

    @Autowired
    CliExecutor cliExecutor;

    @Autowired
    InboundClient inboundClient;

    @Override
    public void run(String... args) throws Exception {

//        var accessControlId = bus.execute(new CreateAccessControlCommand("test", "ABC")).getId();
//
//
//        System.out.println(bus.execute(new AllowAccessControlCommand(
//                accessControlId,
//                "127.0.0.1/32", null
//        )));
//
//        System.out.println(bus.execute(new DenyAccessControlCommand(
//                accessControlId,
//                "127.0.0.5/32", null
//        )));
//
//        System.out.println(bus.execute(new DenyAccessControlCommand(
//                accessControlId,
//                "127.0.0.2/32", null
//        )));
//
//        System.out.println(bus.execute(new DenyAccessControlCommand(
//                accessControlId,
//                "127.0.0.3/32", null
//        )));

//        var xml = bus.execute(new GenerateAccessControlXmlCommand());
//        var cache = cacheManager.getCache("configurations");
//        assert cache != null;
//        cache.put("fs:acl", xml);
//
//        System.out.println(cache.get("fs:acl", String.class));
//
//        bus.execute(new ToggleAccessControlCommand(
//                accessControlId
//        ));

//        System.out.println(bus.execute(new GenerateAccessControlXmlCommand(null)));

//        var cli = new Lua("domain_agent_list", "d9bfc122-66dc-4ef8-b730-270baf75f8a5");
//
//        System.out.println(cliExecutor.submit(cli).get(0).getBodyLines());

        bus.execute(new CreateDomainCommand("default"));
        bus.execute(new CreateExtensionCommand("1000", "default", null));
    }
}
