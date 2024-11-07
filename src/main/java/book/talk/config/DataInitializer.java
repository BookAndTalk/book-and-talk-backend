package book.talk.config;

import book.talk.user.domain.User;
import book.talk.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void run(String... args) throws Exception {

        for (int i = 1; i <= 5; i++) {
            String email = "email" + Integer.toString(i) + "@gmail.com";
            String password = "password" + Integer.toString(i);

            User user = User.builder()
                    .email(email)
                    .password(bCryptPasswordEncoder.encode(password))
                    .build();
            userRepository.save(user);
        }
    }
}
