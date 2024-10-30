package book.talk.service;

import book.talk.config.AladinApiProperties;
import book.talk.entity.AladinIsbnEntity;
import book.talk.entity.AladinIsbnRepository;
import book.talk.model.aladin.AladinRequest;
import book.talk.model.aladin.AladinResponse;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
//알라딘 API에서 isbn 가져오기
public class AladinApiService {

    private final AladinApiProperties aladinApiProperties;
    private final RestTemplate restTemplate;
    private final AladinIsbnRepository aladinIsbnRepository;

    public AladinApiService(AladinApiProperties aladinApiProperties, RestTemplate restTemplate, AladinIsbnRepository aladinIsbnRepository) {
        this.aladinApiProperties = aladinApiProperties;
        this.restTemplate = restTemplate;
        this.aladinIsbnRepository = aladinIsbnRepository;
    }

    // Aladin API url 생성 메서드
    private String buildAladinApiUrl(String queryType) {
        return aladinApiProperties.getBaseUrl() + "?ttbkey=" + aladinApiProperties.getKey()
                + "&QueryType=" + queryType
                + "&MaxResults=10&start=1&SearchTarget=Book&output=JS&Version=20131101";
    }

    // AladinRequest를 받아서 API에서 ISBN 리스트를 저장하고, 반환하는 메서드
    public List<AladinResponse> getIsbn(AladinRequest aladinRequest) {
        String url = buildAladinApiUrl(aladinRequest.getQueryType());
        List<AladinResponse> isbnList = fetchIsbnFromAladinApi(url);

        // 각 ISBN을 해당 카테고리와 함께 저장
        String category = aladinRequest.getQueryType();
        for (AladinResponse isbn : isbnList) {
            if (isbn.getIsbn13() != null && !isbn.getIsbn13().isEmpty()) {
                if (!aladinIsbnRepository.existsById(isbn.getIsbn13())) {
                    aladinIsbnRepository.save(new AladinIsbnEntity(isbn.getIsbn13(), category));
                }
            }
        }
        return isbnList;
    }

    // 알라딘 API로부터 ISBN 리스트를 추출하는 메서드
    private List<AladinResponse> fetchIsbnFromAladinApi(String url) {
        // API 호출 및 JSON 응답을 AladinIsbnEntity로 받기
        AladinApiResponse aladinApiResponse = restTemplate.getForObject(url, AladinApiResponse.class);

        List<AladinResponse> isbnList = new ArrayList<>();


//        if (response != null) {
//            System.out.println("Response: " + response.toString());
//            if (response.getItem() != null) {
//                response.getItem().forEach(item -> System.out.println("ISBN13: " + item.getIsbn13()));
//            } else {
//                System.out.println("Item list is null");
//            }
//        } else {
//            System.out.println("Response is null");
//        }
//        System.out.println("Requesting URL: " + url);

        // 응답에서 ISBN을 추출하여 리스트로 반환
        if (aladinApiResponse != null && aladinApiResponse.getItem() != null) {
            for (AladinApiResponse.Item item : aladinApiResponse.getItem()) {
                isbnList.add(new AladinResponse(item.getIsbn13()));
            }
        }
        return isbnList;
    }

    @Getter
    @Setter
    // 알라딘 API 응답 구조를 위한 내부 클래스 (응답에 맞게 수정 가능)
    public static class AladinApiResponse {
        private List<Item> item;

        @Getter
        @Setter
        public static class Item {
            private String isbn13;
        }
    }
}
