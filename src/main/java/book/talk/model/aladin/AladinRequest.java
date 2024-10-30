package book.talk.model.aladin;

import lombok.Data;

@Data
public class AladinRequest {
    private String queryType; // 주목할 만한 신간, 편집자 추천, 베스트셀러 구분을 위한 타입

    public AladinRequest() {
    }

    public AladinRequest(String queryType) {
        this.queryType = queryType;
    }
}
