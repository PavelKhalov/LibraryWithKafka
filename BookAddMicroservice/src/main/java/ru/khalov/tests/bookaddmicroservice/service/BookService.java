package ru.khalov.tests.bookaddmicroservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.khalov.tests.bookaddmicroservice.entity.BookEntity;
import ru.khalov.tests.bookaddmicroservice.repository.BookRepository;
import ru.khalov.tests.core.event.AddBookEvent;
import tools.jackson.databind.ObjectMapper;

@Slf4j
@RequiredArgsConstructor
@Service
public class BookService {

    private final StringRedisTemplate stringRedisTemplate;
    private final ObjectMapper objectMapper;
    private final BookRepository bookRepository;


    @KafkaListener(topics = "topic-add", containerFactory = "containerFactory")
    public void addBook(AddBookEvent event){
        log.info("start method add book in microservice");

        var book = new BookEntity();
        book.setTitle(event.getTitle());
        book.setAuthor(event.getAuthor());
        book.setGenre(event.getGenre());
        book.setYearRelease(event.getYearRelease());
        try {
            var bookSaved = bookRepository.save(book);
            log.info("book save successfully");

            log.info("start save bok in cache");
            var json = objectMapper.writeValueAsString(bookSaved);
            stringRedisTemplate.opsForValue().append("book:"+bookSaved.getId(), json);
            log.info("add book in cache successful");
        } catch (Exception e){
            log.error("Error to save book: {}", e.getMessage());
            throw e;
        }

    }
}
