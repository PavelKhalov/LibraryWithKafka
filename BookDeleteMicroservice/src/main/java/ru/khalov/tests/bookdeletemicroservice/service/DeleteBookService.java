package ru.khalov.tests.bookdeletemicroservice.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.khalov.tests.bookdeletemicroservice.repository.BookRepository;
import ru.khalov.tests.core.event.DeleteBookEvent;
import tools.jackson.databind.ObjectMapper;

import java.io.StringReader;


@RequiredArgsConstructor
@Service
public class DeleteBookService {

    private final ObjectMapper objectMapper;
    private final StringRedisTemplate stringRedisTemplate;
    private final BookRepository bookRepository;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @KafkaListener(topics = "topic-delete", containerFactory = "containerFactory")
    public void DeleteBook(DeleteBookEvent event) {
        log.info("Start delete Book");
        log.info("Received message {}", event.getBookId());
        try {
            bookRepository.deleteById(Long.parseLong(event.getBookId()));
            log.info("delete successfully");
            log.info("start delete book from cache");

            stringRedisTemplate.delete("book:"+event.getBookId());
            log.info("delete book from cache successful");
        } catch (Exception e){
            log.error("delete book failed: {}", e.getMessage());
        }


    }
}
