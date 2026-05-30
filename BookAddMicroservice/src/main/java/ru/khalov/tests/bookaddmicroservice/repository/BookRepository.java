package ru.khalov.tests.bookaddmicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.khalov.tests.bookaddmicroservice.entity.BookEntity;

public interface BookRepository extends JpaRepository<BookEntity, Long> {
}
