package ru.khalov.tests.bookupdatemicroservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.khalov.tests.bookupdatemicroservice.entity.BookEntity;
import ru.khalov.tests.bookupdatemicroservice.repository.BookRepository;
import ru.khalov.tests.core.event.EditBookEvent;
import tools.jackson.databind.ObjectMapper;


@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
@Slf4j
public class BookService {

    private final ObjectMapper objectMapper;
    private final StringRedisTemplate stringRedisTemplate;
    private final BookRepository bookRepository;


    @Transactional(readOnly = false)
    @KafkaListener(topics = "topic-edit", containerFactory = "containerFactory")
    public void editBook(EditBookEvent event){
        log.info("start edit book with id: {}", event.getBookId());
        var bookOnSave = new BookEntity(
                Long.parseLong(event.getBookId()),
                event.getTitle(),
                event.getAuthor(),
                event.getGenre(),
                event.getYearRelease()
        );

        try {
            bookRepository.save(bookOnSave);
            log.info("Edit book with id:{}, successfully", event.getBookId());

            log.info("Start update book in cache");
            var json = objectMapper.writeValueAsString(bookOnSave);
            stringRedisTemplate.opsForValue().set("book:" + bookOnSave.getId().toString(), json);
            log.info("update book with id: {}, successful", bookOnSave.getId());
        }catch (Exception e){
            log.error("update error: {}", e.getMessage());
        }
    }

}
