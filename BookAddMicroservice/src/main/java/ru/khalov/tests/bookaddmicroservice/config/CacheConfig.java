package ru.khalov.tests.bookaddmicroservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.*;
import ru.khalov.tests.bookaddmicroservice.entity.BookEntity;
import tools.jackson.databind.ObjectMapper;

@Configuration
public class CacheConfig {

    @Bean
    public RedisTemplate<String, BookEntity> redisTemplate(
            RedisConnectionFactory redisConnectionFactory,
            ObjectMapper objectMapper){
        RedisTemplate<String, BookEntity> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        redisTemplate.setKeySerializer(new StringRedisSerializer());
        // Для конкретного типа
        redisTemplate.setValueSerializer(new JacksonJsonRedisSerializer<>(BookEntity.class));

        //Для всех типов
        //redisTemplate.setValueSerializer(GenericJacksonJsonRedisSerializer.builder().build());

        //// Важный метод который указывает что он окончен и все properties поставлены
        redisTemplate.afterPropertiesSet();

        return redisTemplate;
    }

}
