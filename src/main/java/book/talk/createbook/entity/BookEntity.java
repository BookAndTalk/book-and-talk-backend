package book.talk.createbook.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class BookEntity {
    @Id
    private Long bookId;

    private String thumbnail;
    private String bookTitle;
    private int groupCount;
}
