package ru.khalov.tests.bookservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.khalov.tests.core.event.FindBookEvent;
import org.springframework.kafka.support.SendResult;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;


@Slf4j
@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService{

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public String findAllBook() {
        log.info("Send in kafka message about find all books");
        var event = new FindBookEvent();

        ProducerRecord record = new ProducerRecord<>("book-topic", event);

        CompletableFuture<SendResult<String, FindBookEvent>> future = kafkaTemplate.send(record);
        future.whenComplete((result, exception) -> {
            if (exception != null){
                log.error("Error send message in kafka");
            } else {
                log.info("All good, message sended");
            }
        });

        return UUID.randomUUID().toString();

    }

    @Override
    public String findBookById(String id) {
        log.info("try send message about what need find book by id");
        String messageId = UUID.randomUUID().toString();
        var event = new FindBookEvent(messageId,id);

        ProducerRecord record = new ProducerRecord<>("book-topic", event);

        CompletableFuture<SendResult<String, FindBookEvent>> future = kafkaTemplate.send(record);
        future.whenComplete((result, exception) -> {
            if(exception != null){
                log.error("Send message failed");
            } else {
                log.info("Send message successful");
            }
        });

        return id;
    }
}
