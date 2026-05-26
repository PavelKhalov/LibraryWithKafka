package ru.khalov.tests.bookservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.khalov.tests.bookservice.service.BookServiceImpl;

@Slf4j
@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {
    private final BookServiceImpl bookService;

    @GetMapping
    public ResponseEntity<String> findAllBook(){
        log.info("Called method findAllBook from controller (provider in kafka)");

        return ResponseEntity.status(HttpStatus.OK).body(String.valueOf(bookService.findAllBook()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> findBookById(@PathVariable ("id") Long id){
        log.info("Called method findBookById from controller (provider in kafka)");
        return ResponseEntity.status(HttpStatus.FOUND).body(bookService.findBookById(String.valueOf(id)));
    }
}
