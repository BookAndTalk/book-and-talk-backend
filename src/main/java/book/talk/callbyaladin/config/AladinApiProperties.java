package book.talk.callbyaladin.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "aladin.api")

//application.properties에 정의된 알라딘 API 관련 설정 값을 매핑
public class AladinApiProperties {
    private String key;
    private String baseUrl;
}
