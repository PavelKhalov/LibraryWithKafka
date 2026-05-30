package ru.khalov.tests.bookupdatemicroservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.khalov.tests.bookupdatemicroservice.entity.BookEntity;
import ru.khalov.tests.bookupdatemicroservice.repository.BookRepository;
import ru.khalov.tests.core.event.EditBookEvent;


@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
@Slf4j
public class BookService {

    private final BookRepository bookRepository;


    @Transactional(readOnly = false)
    @KafkaListener(topics = "topic-edit")
    public void editBook(EditBookEvent event){

        var bookOnSave = new BookEntity(
                Long.parseLong(event.getBookId()),
                event.getTitle(),
                event.getAuthor(),
                event.getGenre(),
                event.getYearRelease()
        );

        bookRepository.save(bookOnSave);
    }

}
