package ru.khalov.tests.bookfindmicroservice.service.dto;

public record BookDto(
        String title,
        String author,
        String genre,
        Integer yearRelease
) {
}
