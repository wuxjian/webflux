package the.wuxjian.webflux.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;

/**
 * Created by wuxjian 2021/8/3
 */
@Configuration
public class RedisConfig {
//    @Bean
//    public ReactiveRedisConnectionFactory lettuceConnectionFactory() {
//
//        LettuceClientConfiguration clientConfig = LettuceClientConfiguration.builder()
//                .commandTimeout(Duration.ofSeconds(2))
//                .shutdownTimeout(Duration.ZERO)
//                .build();
//        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration("47.117.42.24", 6379);
//        configuration.setPassword(RedisPassword.of("Csih@1234"));
//        return new LettuceConnectionFactory(configuration, clientConfig);
//    }

//    @Bean("reactiveRedisTemplate")
//    ReactiveRedisTemplate<String, String> reactiveRedisTemplate(ReactiveRedisConnectionFactory lettuceConnectionFactory) {
//        return new ReactiveRedisTemplate<>(lettuceConnectionFactory, RedisSerializationContext.string());
//    }
}
