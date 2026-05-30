package ru.khalov.tests.bookfindmicroservice.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.khalov.tests.bookfindmicroservice.entity.BookEntity;
import ru.khalov.tests.bookfindmicroservice.mapper.Mapper;
import ru.khalov.tests.bookfindmicroservice.repository.BookRepository;
import ru.khalov.tests.core.dto.BookDto;
import ru.khalov.tests.core.event.FindBookEvent;
import ru.khalov.tests.core.responce.BookResponseEvent;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class FindBookServiceImpl implements FindBookService{

    private final Mapper mapper;
    private final BookRepository bookRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    @KafkaListener(topics = "topic-find", groupId = "${spring.kafka.consumer.group-id}")
    public void findAllBook(FindBookEvent event) {
        log.info("received event: find all book");
        List<BookEntity> entities = bookRepository.findAll();
        List<BookDto> books = entities.stream().map(mapper::toDto).toList();

        var response = new BookResponseEvent(event.getMessageId(), books);
        kafkaTemplate.send("topic-accept-responce", response);

    }

    @Override
    @KafkaListener(topics = "topic-find", groupId = "${spring.kafka.consumer.group-id}")
    public void findById(FindBookEvent event) {
        log.info("received event: find book by id");
        BookDto dto = mapper.toDto(bookRepository.findById(Long.parseLong(event.getBookId())).orElseThrow(EntityNotFoundException::new));

        String messageId = UUID.randomUUID().toString();
        var response = new BookResponseEvent(messageId, dto);
        kafkaTemplate.send("topic-accept-responce", response);
        log.info("Send in topic topic-accept-responce message successfully");
    }
}
