package ru.khalov.tests.bookservice.service;

import ru.khalov.tests.core.dto.BookDto;

import java.util.List;

public interface BookService {

    List<String> findAllBook();
    String findBookById(String id);
    String deleteBook(Long id);
    String createBook(BookDto dto);
    String updateBook(Long id, BookDto dto);

}
