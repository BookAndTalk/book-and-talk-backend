package book.talk.config;

import book.talk.createbook.entity.BookEntity;
import book.talk.createbook.entity.BookRepository;
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

    @Autowired
    private BookRepository bookRepository;

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

        BookEntity bookEntity1 = new BookEntity();
        bookEntity1.setBookTitle("소년이 온다");
        bookEntity1.setIsbn13(9788936434120L);
        bookRepository.save(bookEntity1);

        BookEntity bookEntity2 = new BookEntity();
        bookEntity2.setBookTitle("작별하지 않는다");
        bookEntity2.setIsbn13(9788954682152L);
        bookRepository.save(bookEntity2);

        BookEntity bookEntity3 = new BookEntity();
        bookEntity3.setBookTitle("흰");
        bookEntity3.setIsbn13(9788954651134L);
        bookRepository.save(bookEntity3);
    }
}
