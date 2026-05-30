package ru.khalov.tests.bookservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import ru.khalov.tests.core.dto.BookDto;
import ru.khalov.tests.core.event.AddBookEvent;
import ru.khalov.tests.core.event.DeleteBookEvent;
import ru.khalov.tests.core.event.FindBookEvent;
import org.springframework.kafka.support.SendResult;
import ru.khalov.tests.core.responce.BookResponseEvent;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;


@Slf4j
@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService{

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final ManualCacheService cacheService;

    @Override
    public List<String> findAllBook() {
        return cacheService.findAll();
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

    @Override
    public String createBook(BookDto dto){

        log.info("try send message in kafka about save book");
        String messageId = UUID.randomUUID().toString();

        var event = new AddBookEvent(
                dto.title(),
                dto.author(),
                dto.genre(),
                dto.yearRelease()
        );


        CompletableFuture<SendResult<String, Object>> future =
                kafkaTemplate.send("topic-add", messageId, event);
        future.whenComplete( (result, exception) -> {
            if(exception != null){
                log.error("Error to send message in kafka");
            }else{
                log.info("Message send successfully");
            }
        });

        return messageId;
    }

    @Override
    public String updateBook(Long id, BookDto dto) {
        return null;
    }

    @Override
    public String deleteBook(Long id){
        log.info("Start method delete book, after send message in kafka");

        var event = new DeleteBookEvent(id.toString());

        CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send("topic-delete", event);
        future.whenComplete((result, exception) -> {
            if(exception != null){
                log.error("Error to send message in kafka");
            }else{
                log.info("Message send successfully");
            }
        });
        return id.toString();
    }


    @KafkaListener(topics = "topic-accept-responce", containerFactory = "containerFactory")
    public String acceptMessage (
            @Payload BookResponseEvent bookResponseEvent
    ) {
        return bookResponseEvent.toString();
    }

}
