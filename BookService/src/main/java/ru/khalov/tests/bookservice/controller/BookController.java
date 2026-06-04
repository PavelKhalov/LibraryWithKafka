package ru.khalov.tests.bookservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.khalov.tests.bookservice.service.BookServiceImpl;
import ru.khalov.tests.bookservice.service.ManualCacheService;
import ru.khalov.tests.core.dto.BookDto;

import java.util.List;

@Tag(name = "All methods of book controller", description = "controlling books")
@Slf4j
@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {
    private final BookServiceImpl bookService;
    private final ManualCacheService cacheService;

    @GetMapping("/all")
    @ApiResponse(responseCode = "200", description = "All books find")
    @Operation(summary = "find all book")
    public ResponseEntity<List<String>> findAllBook(){
        log.info("Find all books from cache");
        return ResponseEntity.status(HttpStatus.OK).body(cacheService.findAll());
    }

    @ApiResponse(responseCode = "201", description = "book created")
    @Operation(summary = "create book")
    @PostMapping("/create")
    public ResponseEntity<String> createBook(@RequestBody BookDto bookDto) {
        log.info("Called method createBook in controller");
        bookService.createBook(bookDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("заглушка");
    }


    @ApiResponse(responseCode = "204", description = "delete book")
    @Operation(summary = "delete book")
    @Parameter(name = "id", in = ParameterIn.PATH, required = true, example = "1")
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id){
        log.info("Called method deleteBook from controller");
        bookService.deleteBook(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @Operation(summary = "delete book")
    @ApiResponse(responseCode = "200", description = "book find")
    @Parameter(name = "id", required = true, in = ParameterIn.PATH)
    @PostMapping("/{id}/update")
    public ResponseEntity<String> updateBook(@RequestBody BookDto bookDto,
                                             @PathVariable Long id){
        log.info("Called method updateBook from controller");
        bookService.updateBook(id, bookDto);

        return ResponseEntity.ok().body(String.valueOf(id));
    }

    @Operation(summary = "find book by id")
    @ApiResponse(responseCode = "200", description = "book find")
    @Parameter(name = "id", required = true, in = ParameterIn.PATH)
    @GetMapping("/{id}")
    public ResponseEntity<String> findBookById(@PathVariable Long id){
        log.info("Find book with id: {} in cache", id);
        return ResponseEntity.status(HttpStatus.OK).body(cacheService.findById(id).toString());
    }
}
