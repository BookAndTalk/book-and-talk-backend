package book.talk.callbyaladin.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class AladinIsbnEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String isbn13;
    private String category;

    public AladinIsbnEntity() {
    }

    public AladinIsbnEntity(String isbn13, String category) {
        this.isbn13 = isbn13;
        this.category = category;
    }
}
