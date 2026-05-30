package ru.khalov.tests.bookdeletemicroservice.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.khalov.tests.bookdeletemicroservice.repository.BookRepository;

@RequiredArgsConstructor
@Service
public class DeleteBookService {

    private final BookRepository bookRepository;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @KafkaListener(topics = "topic-delete")
    public void DeleteBook(){
        log.info("Start delete Book");

        bookRepository.delete();

    }

}
