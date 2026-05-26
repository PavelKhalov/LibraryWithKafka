package ru.khalov.tests.core.event;

public class EditBookEvent {
    private String bookId;
    private String title;
    private String author;
    private String genre;
    private Integer yearRelease;

    public EditBookEvent() {
    }

    public EditBookEvent(String bookId, String title, String author, String genre, Integer yearRelease) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.yearRelease = yearRelease;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getYearRelease() {
        return yearRelease;
    }

    public void setYearRelease(Integer yearRelease) {
        this.yearRelease = yearRelease;
    }
}
