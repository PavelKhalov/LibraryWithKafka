package ru.khalov.tests.bookfindmicroservice.service;

import ru.khalov.tests.core.event.FindBookEvent;

public interface FindBookService {
    void findAllBook(FindBookEvent event);
    void findById(FindBookEvent event);
}
