package ru.khalov.tests.core.responce;

import ru.khalov.tests.core.dto.BookDto;

import java.util.List;

public class BookResponseEvent {
    private String messageId;
    private List<BookDto> books;
    private BookDto book;

    public BookResponseEvent(){}

    public BookResponseEvent(String messageId, List<BookDto> books) {
        this.messageId = messageId;
        this.books = books;
    }

    public BookResponseEvent(String messageId, BookDto book) {
        this.messageId = messageId;
        this.book = book;
    }


    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public List<BookDto> getBooks() {
        return books;
    }

    public void setBooks(List<BookDto> books) {
        this.books = books;
    }

    public BookDto getBook() {
        return book;
    }

    public void setBook(BookDto book) {
        this.book = book;
    }
}
