package luongld.freeswitch.appconfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

@Configuration
@EnableCaching
public class SpringCacheConfig {

//    @Bean
//    @ConditionalOnProperty(value = "spring.cache.type", havingValue = "redis")
//    public CacheManager cacheManager(
//            RedisConnectionFactory connectionFactory,
//            @Value("${app.pbx.cache.ttl:60}") int ttl) {
//
//        var stringSerializer = RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer());
//        var config = RedisCacheConfiguration.defaultCacheConfig()
//                .entryTtl(Duration.ofMillis(ttl))
//                .disableCachingNullValues()
//                .serializeValuesWith(stringSerializer)
//                .serializeKeysWith(stringSerializer);
//
//        return RedisCacheManager.builder(connectionFactory).enableStatistics().cacheDefaults(config).build();
//    }

    @Bean
    @ConditionalOnBean(RedisConnectionFactory.class)
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory connectionFactory) {
        var template = new RedisTemplate<String, String>();
        template.setConnectionFactory(connectionFactory);

        template.afterPropertiesSet();

        return template;
    }
}


