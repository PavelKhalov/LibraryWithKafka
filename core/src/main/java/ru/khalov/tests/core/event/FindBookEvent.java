package ru.khalov.tests.core.event;

public class FindBookEvent {
    private String messageId;
    private String bookId;

    public FindBookEvent (){}

    public FindBookEvent(String messageId, String bookId){
        this.messageId = messageId;
        this.bookId = bookId;
    }

    public String getMessageId(){
        return messageId;
    }

    public String getBookId() {
        return bookId;
    }

}
