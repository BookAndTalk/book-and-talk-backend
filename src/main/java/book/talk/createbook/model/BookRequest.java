package book.talk.createbook.model;

import lombok.Data;

@Data
public class BookRequest {
    private String thumbnail;
    private String bookTitle;
    private Long isbn13;
}
