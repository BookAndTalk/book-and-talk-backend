package book.talk.auth.config.oauth;

import book.talk.user.domain.User;
import book.talk.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class OAuth2UserCustomService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        System.out.println("--------loadUser---------");
        OAuth2User user = super.loadUser(userRequest); // ❶ 요청을 바탕으로 유저 정보를 담은 객체 반환
        saveOrUpdate(user);

        return user;
    }

    // ❷ 유저가 있으면 업데이트, 없으면 유저 생성
    private User saveOrUpdate(OAuth2User oAuth2User) {
        Map<String, Object> attributes = oAuth2User.getAttributes();

        Object kakaoAccount = attributes.get("kakao_account");
        LinkedHashMap accountMap = (LinkedHashMap) kakaoAccount;
        String email = (String) accountMap.get("email");

        Object profile = accountMap.get("profile");
        LinkedHashMap profileMap = (LinkedHashMap) profile;
        String nickname = (String) profileMap.get("nickname");


        System.out.println(email);
//        String email = (String) attributes.get("email");
//        String name = (String) attributes.get("name");

        User user = userRepository.findByEmail(email)
                .map(entity -> entity.update(nickname))
                .orElse(User.builder()
                        .email(email)
                        .nickname(nickname)
                        .build());

        return userRepository.save(user);
    }
}

