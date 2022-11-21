package luongld.freeswitch;

import luongld.cqrs.Bus;
import luongld.freeswitch.esl.InboundClient;
import luongld.freeswitch.esl.cli.CliExecutor;
import luongld.freeswitch.xml.Document;
import luongld.freeswitch.xml.sections.DirectorySection;
import luongld.freeswitch.xml.sections.directory.Directory;
import luongld.freeswitch.xml.sections.directory.DirectoryDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

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
    RedisTemplate<String, String> redisTemplate;

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

//        bus.execute(new CreateDomainCommand("default"));
//        bus.execute(new CreateExtensionCommand("1000", "default", null));


        var jaxbContext = JAXBContext.newInstance(Document.class);
        var marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        var domain = new DirectoryDomain("voice.metechvn.com")
                .param("jsonrpc-allowed-methods", "verto")
                .param("jsonrpc-allowed-event-channels", "demo,conference,presence");


        var directory = new Directory("1000", null)
                .param("password", "Abc@123")
                .param("dial-string", "{sip_invite_domain=${domain_name},leg_timeout=${call_timeout},presence_id=${dialed_user}@${dialed_domain}}${sofia_contact(*/${dialed_user}@${dialed_domain})}")
                .variable("domain_name", "voice.metechvn.com")
                .variable("identifier", "1000@voice.metechvn.com")
                .variable("call_timeout", 20)
                .variable("record", "all")
                .variable("record_stereo", "true");

        var directorySection = new DirectorySection(domain, directory);

        String xml = "";
        try (var os = new ByteArrayOutputStream()) {

            marshaller.marshal(directorySection, os);
            xml = os.toString();
        } catch (IOException ignored) {

        }


        redisTemplate.opsForHash().put("directories", "1000@voice.metechvn.com", xml);
    }
}
