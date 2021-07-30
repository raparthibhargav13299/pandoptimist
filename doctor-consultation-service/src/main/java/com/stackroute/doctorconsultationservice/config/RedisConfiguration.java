package com.stackroute.doctorconsultationservice.config;
import com.stackroute.doctorconsultationservice.model.Doctor;
import com.stackroute.doctorconsultationservice.model.DoctorRedis;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
@Configuration
@EnableRedisRepositories
public class RedisConfiguration {
    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private Integer port;
    /**
     * Take host and port from worker service
     * If port and host not given then by default it will take host=localhost and port=6379
     * For connection with redis creating bean and this bean is require to redis template.
     * @return
     */
    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        LettuceConnectionFactory connectionFactory = new LettuceConnectionFactory(new RedisStandaloneConfiguration(host, port));
        return connectionFactory;
    }
    /**
     * It is use for get and set value by key in redis.
     * @return
     */
//    @Bean
//    public RedisTemplate<String, Object> redisTemplate() {
//        RedisTemplate<String, Object> template = new RedisTemplate();
//        template.setConnectionFactory(redisConnectionFactory());
//        return template;
//    }
    /**
     * It is use for pub-sub in redis
     * @return
     */
    @Bean
    public RedisTemplate<String, DoctorRedis> redisTemplateForPublish() {
        RedisTemplate<String, DoctorRedis> template = new RedisTemplate();
        template.setConnectionFactory(redisConnectionFactory());
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new Jackson2JsonRedisSerializer<>(DoctorRedis.class));
        return template;
    }
}
