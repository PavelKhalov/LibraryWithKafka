package ru.khalov.tests.bookupdatemicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.khalov.tests.bookupdatemicroservice.entity.BookEntity;

@Repository
public interface BookRepository extends JpaRepository <BookEntity, Long> {
}
