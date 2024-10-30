package book.talk.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "kakao.api")

//application.properties에 정의된 kakao API 관련 설정 값을 매핑
public class KakaoApiProperties {
    private String key;
    private String baseUrl;
}
