package ru.khalov.tests.core.dto;

public record BookDto(
        String title,
        String author,
        String genre,
        Integer yearRelease
) {
}
