package luongld.fcluster;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class FClusterApplication {

    private static Class<FClusterApplication> clazz;
    private static ConfigurableApplicationContext context;

    public static void main(String[] args) {
        FClusterApplication.context = SpringApplication.run(clazz, args);
    }

}
