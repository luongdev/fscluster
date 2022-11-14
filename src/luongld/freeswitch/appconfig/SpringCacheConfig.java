package luongld.freeswitch.appconfig;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
@EnableCaching
public class SpringCacheConfig {

    @Bean
    @ConditionalOnProperty(value = "spring.cache.type", havingValue = "simple")
    public CacheManager cacheManager() {
        var cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(Arrays.asList(
                new ConcurrentMapCache(Caches.Configuration),
                new ConcurrentMapCache(Caches.Dialplan),
                new ConcurrentMapCache(Caches.Diretory)
        ));

        return cacheManager;
    }
}


