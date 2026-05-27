package ru.khalov.tests.bookfindmicroservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.khalov.tests.bookfindmicroservice.model.Book;
import ru.khalov.tests.bookfindmicroservice.repository.BookRepository;
import ru.khalov.tests.bookfindmicroservice.service.dto.BookDto;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FindBookServiceImpl implements FindBookService{

    private final BookRepository bookRepository;

    @Override
    public List<BookDto> findAllBook() {
        return List.of();
    }

    @Override
    public BookDto findById(Long id) {
        return null;
    }
}
