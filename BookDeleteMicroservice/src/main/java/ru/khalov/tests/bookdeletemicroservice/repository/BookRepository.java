package ru.khalov.tests.bookdeletemicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.khalov.tests.core.event.DeleteBookEvent;

@Repository
public interface BookRepository extends JpaRepository<DeleteBookEvent, Long> {
}
