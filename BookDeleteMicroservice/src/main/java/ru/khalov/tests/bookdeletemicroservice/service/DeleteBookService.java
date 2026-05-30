package ru.khalov.tests.bookdeletemicroservice.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.khalov.tests.bookdeletemicroservice.repository.BookRepository;
import ru.khalov.tests.core.event.DeleteBookEvent;


@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class DeleteBookService {

    private final BookRepository bookRepository;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Transactional(readOnly = false)
    @KafkaListener(topics = "topic-delete")
    public void DeleteBook(DeleteBookEvent event){
        log.info("Start delete Book");
        log.info("Received message {}", event.getBookId());
        bookRepository.deleteById(Long.parseLong( event.getBookId()));
        log.info("delete successfully");
    }

}
