package ru.khalov.tests.core.event;

public class FindBookEvent {
    private String bookId;
    private String title;

    public FindBookEvent (){}

    public FindBookEvent(String bookId){
        this.bookId = bookId;
    }
    public FindBookEvent(String bookId, String title){
        this.bookId = bookId;
        this.title = title;
    }

    public String getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }
}
