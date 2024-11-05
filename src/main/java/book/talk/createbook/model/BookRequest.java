package book.talk.createbook.model;

import lombok.Data;

@Data
public class BookRequest {
    private Long bookId;
    private String thumbnail;
    private String bookTitle;
    private int isbn13;
}
