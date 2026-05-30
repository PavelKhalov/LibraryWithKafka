package ru.khalov.tests.bookservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.khalov.tests.bookservice.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
