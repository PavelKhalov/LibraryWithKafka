package ru.khalov.tests.bookfindmicroservice.model;

public record Book (
        String title,
        String author,
        String genre,
        Integer yearRelease
){
}
