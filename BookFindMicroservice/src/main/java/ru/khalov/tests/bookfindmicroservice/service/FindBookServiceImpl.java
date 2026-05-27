package ru.khalov.tests.bookfindmicroservice.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.core.support.RepositoryMethodInvocationListener;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.khalov.tests.bookfindmicroservice.entity.BookEntity;
import ru.khalov.tests.bookfindmicroservice.mapper.Mapper;
import ru.khalov.tests.bookfindmicroservice.model.Book;
import ru.khalov.tests.bookfindmicroservice.repository.BookRepository;
import ru.khalov.tests.bookfindmicroservice.service.dto.BookDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class FindBookServiceImpl implements FindBookService{

    private final Mapper mapper;
    private final BookRepository bookRepository;

    @Override
    @KafkaListener(topics = "topic-find", groupId = "${spring.kafka.consumer.group-id}")
    public List<BookDto> findAllBook() {
        List<BookEntity> entities = bookRepository.findAll();
        return entities.stream().map(mapper::toDto).toList();
    }


    @Override
    @KafkaListener(topics = "find-topic", groupId = "${spring.kafka.consumer.group-id}")
    public BookDto findById(Long id) {
        return mapper.toDto(bookRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }
}
