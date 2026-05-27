package ru.khalov.tests.bookfindmicroservice.mapper;

import org.springframework.stereotype.Component;
import ru.khalov.tests.bookfindmicroservice.entity.BookEntity;
import ru.khalov.tests.bookfindmicroservice.service.dto.BookDto;

@Component
public class Mapper {
    public BookDto toDto (BookEntity entity) {
        return new BookDto(
                entity.getTitle(),
                entity.getAuthor(),
                entity.getGenre(),
                entity.getYearRelease());
    }

    public BookEntity toEntity(Long id, BookDto dto){
        return new BookEntity(
                id,
                dto.title(),
                dto.author(),
                dto.genre(),
                dto.yearRelease()
        );
    }
}
