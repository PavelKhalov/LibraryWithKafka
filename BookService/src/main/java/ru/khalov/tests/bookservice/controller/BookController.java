package ru.khalov.tests.bookservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.khalov.tests.bookservice.service.BookServiceImpl;
import ru.khalov.tests.bookservice.service.ManualCacheService;
import ru.khalov.tests.core.dto.BookDto;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {
    private final BookServiceImpl bookService;
    private final ManualCacheService cacheService;

    @GetMapping
    public ResponseEntity<List<String>> findAllBook(){
        log.info("Find all books from cache");
        return ResponseEntity.status(HttpStatus.OK).body(cacheService.findAll());
    }

    @PostMapping("/create")
    public ResponseEntity<String> createBook(@RequestBody BookDto bookDto) {
        log.info("Called method createBook in controller");
        bookService.createBook(bookDto);
        return  null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> findBookById(@PathVariable Long id){
        log.info("Find book with id: {} in cache", id);
        return ResponseEntity.status(HttpStatus.FOUND).body(cacheService.findById(id));
    }
}
