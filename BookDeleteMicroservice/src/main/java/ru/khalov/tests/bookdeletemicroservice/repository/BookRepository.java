package ru.khalov.tests.bookdeletemicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.khalov.tests.bookdeletemicroservice.entity.BookEntity;


@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {
}
