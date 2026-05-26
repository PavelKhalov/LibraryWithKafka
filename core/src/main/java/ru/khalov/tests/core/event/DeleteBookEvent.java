package ru.khalov.tests.core.event;

public class DeleteBookEvent {
    private String bookId;

    public DeleteBookEvent() {
    }

    public DeleteBookEvent(String bookId) {
        this.bookId = bookId;
    }

}
