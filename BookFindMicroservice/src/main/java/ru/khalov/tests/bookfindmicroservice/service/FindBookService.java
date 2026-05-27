package ru.khalov.tests.bookfindmicroservice.service;

import ru.khalov.tests.bookfindmicroservice.model.Book;
import ru.khalov.tests.bookfindmicroservice.service.dto.BookDto;

import java.util.List;

public interface FindBookService {
    List<BookDto> findAllBook();
    BookDto findById(Long id);
}
