package ru.khalov.tests.bookservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JacksonJsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import ru.khalov.tests.bookservice.entity.BookEntity;

@Configuration
public class CacheConfig {

    @Bean
    public RedisTemplate<String, BookEntity> redisTemplate(
        RedisConnectionFactory connectionFactory
    ){
        RedisTemplate<String, BookEntity> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new JacksonJsonRedisSerializer<>(BookEntity.class));

        return redisTemplate;
    }
}
