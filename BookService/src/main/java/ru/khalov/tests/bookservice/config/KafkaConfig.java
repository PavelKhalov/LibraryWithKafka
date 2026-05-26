package ru.khalov.tests.bookservice.config;


import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JacksonJsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    @Bean
    public ProducerFactory<String, Object> producerFactory(){
        Map<String, Object> config = new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, );
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JacksonJsonSerializer.class);

        config.put(ProducerConfig.LINGER_MS_CONFIG, 0);
        config.put(ProducerConfig.DELIVERY_TIMEOUT_MS_CONFIG, );
        config.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, );

        config.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, true);
        config.put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, 5);

        return new DefaultKafkaProducerFactory<>(config);
    }

    @Bean
    public NewTopic topicFindBook(){
        return TopicBuilder
                .name("topic-find")
                .partitions(3)
                .replicas(3)
                .build();
    }

    @Bean
    public NewTopic topicAddBook(){
        return TopicBuilder
                .name("topic-add")
                .partitions(3)
                .replicas(3)
                .build();
    }

    @Bean
    public NewTopic topicEditBook(){
        return TopicBuilder
                .name("topic-edit")
                .partitions(3)
                .replicas(3)
                .build();
    }

    @Bean
    public NewTopic topicDeleteBook(){
        return TopicBuilder
                .name("topic-delete")
                .partitions(3)
                .replicas(3)
                .build();
    }


    @Bean
    KafkaTemplate<String, Object> kafkaTemplate (){
        return new KafkaTemplate<>(producerFactory());
    }

}
