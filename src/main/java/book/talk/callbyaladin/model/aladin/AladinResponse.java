package book.talk.callbyaladin.model.aladin;

import lombok.Data;

@Data
public class AladinResponse {
    private String isbn13;

    public AladinResponse(String isbn13) {
        this.isbn13 = isbn13;
    }
}
