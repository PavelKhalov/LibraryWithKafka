package ru.khalov.tests.bookservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "book")
@Getter
@Setter
@ToString
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "genre", nullable = false)
    private String genre;

    @Column(name = "year_release", nullable = false)
    private Integer yearRelease;
}
