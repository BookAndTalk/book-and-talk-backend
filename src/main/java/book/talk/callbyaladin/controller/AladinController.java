package book.talk.callbyaladin.controller;

import book.talk.callbyaladin.model.aladin.AladinRequest;
import book.talk.callbyaladin.model.aladin.AladinResponse;
import book.talk.callbyaladin.service.AladinApiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AladinController {

    private final AladinApiService aladinApiService;

    public AladinController(AladinApiService aladinApiService) {
        this.aladinApiService = aladinApiService;
    }

    // 사용자가 홈화면에 들어올 때 추천 책 리스트 반환
    @GetMapping("/api/recommendations")
    public Map<String, List<AladinResponse>> getRecommendedBooks() {
        // Map을 사용하여 여러 리스트를 반환
        Map<String, List<AladinResponse>> recommendations = new HashMap<>();

        // 추천 책 리스트를 반환 (신간주목할 만한 신간 리스트, 편집자 추천 리스트, 베스트셀러, BlogBest : 블로거 베스트셀러 (국내도서만 조회 가능))
        //ItemNewSpecial : 주목할 만한 신간 리스트
        //ItemEditorChoice : 편집자 추천 리스트
        //Bestseller : 베스트셀러
        //BlogBest : 블로거 베스트셀러

        AladinRequest itemNewSpecial = new AladinRequest("ItemNewSpecial");
        recommendations.put("ItemNewSpecial", aladinApiService.getIsbn(itemNewSpecial));

//        AladinRequest itemEditorChoice = new AladinRequest("ItemEditorChoice");
//        recommendations.put("ItemEditorChoice", aladinApiService.getIsbn(itemEditorChoice));

        AladinRequest bestseller = new AladinRequest("Bestseller");
        recommendations.put("bestseller", aladinApiService.getIsbn(bestseller));

        AladinRequest blogBest = new AladinRequest("BlogBest");
        recommendations.put("BlogBest", aladinApiService.getIsbn(blogBest));

        return recommendations;
    }
}
