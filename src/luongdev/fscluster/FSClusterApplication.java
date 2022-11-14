package luongdev.fscluster;

import luongdev.fscluster.fnode.Test;
import luongdev.fscluster.fnode.TestRepository;
import luongdev.fscluster.master.FSNodeRepository;
import luongdev.fscluster.master.dynamic.NodeHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication(scanBasePackages = "luongdev.fscluster")
public class FSClusterApplication implements CommandLineRunner {

    public final static Class<FSClusterApplication> CLASS = FSClusterApplication.class;
    private static ConfigurableApplicationContext APP = null;

    public static void main(String[] args) {
//        NodeHolder.setNode("6950945f-9c9a-4ec4-8663-8aba2888055f");
        FSClusterApplication.APP = SpringApplication.run(CLASS, args);
    }

    @Autowired
    FSNodeRepository nodeRepository;

    @Autowired
    TestRepository testRepository;

    @Override
//    @Transactional("nodeTransactionManager")
    public void run(String... args) throws Exception {
        var nodes = new String[]{"6950945f-9c9a-4ec4-8663-8aba2888055f", "7950945f-9c9a-4ec4-8663-8aba2888055f", "8950945f-9c9a-4ec4-8663-8aba2888055f"};

        var start = System.currentTimeMillis();
        var content = "Day la content 1";
        for (var node : nodes) {
            NodeHolder.setNode(node);

            testRepository.save(new Test(content));
        }

        System.out.println("Insert lan 1 " + (System.currentTimeMillis() - start));
        Thread.sleep(10000);


        start = System.currentTimeMillis();
        content = "Content 2";
        for (var node : nodes) {
            NodeHolder.setNode(node);

            testRepository.save(new Test(content));
        }
        System.out.println("Insert lan 2 " + (System.currentTimeMillis() - start));
    }
}
