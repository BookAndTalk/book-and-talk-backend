package book.talk.config;

import book.talk.callbyaladin.model.aladin.AladinRequest;
import book.talk.callbyaladin.model.aladin.AladinResponse;
import book.talk.callbyaladin.service.AladinApiService;
import book.talk.createbook.entity.BookEntity;
import book.talk.createbook.entity.BookRepository;
import book.talk.group.domain.Club;
import book.talk.group.service.GroupService;
import book.talk.user.domain.User;
import book.talk.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private GroupService groupService;

    @Autowired
    private AladinApiService aladinApiService;

    @Override
    public void run(String... args) throws Exception {
        AladinRequest bestseller = new AladinRequest("Bestseller");
        List<AladinResponse> isbnList = aladinApiService.getIsbn(bestseller);

        for (int i = 1; i <= 5; i++) {
            String email = "email" + Integer.toString(i) + "@gmail.com";
            String password = "password" + Integer.toString(i);

            // 유저 만들기
            User user = User.builder()
                    .email(email)
                    .password(bCryptPasswordEncoder.encode(password))
                    .build();
            userRepository.save(user);

            // 책 만들기
            BookEntity book = new BookEntity();
            book.setBookTitle("소년이 온다" + Integer.toString(i));
            String isbn13 = isbnList.get(i).getIsbn13();
            Long isbn13Long = Long.parseLong(isbn13);
            book.setIsbn13(isbn13Long);
            bookRepository.save(book);
        }

        for (int i = 1; i <= 5; i++) {
            // 모임 데이터 만들기
            Club club = new Club();
            club.setName("모임" + Integer.toString(i));
            club.setLocation(null);
            club.setGroupImage(null);
            club.setMaxParticipants(5 + i);

            LocalDate date = LocalDate.parse("2024-04-27");
            club.setStartDate(date);
            club.setDuration(2 + i);
            club.setGroupDescription("좋은 모임입니다.");
            LocalDateTime now = LocalDateTime.now();
            club.setCreatedAt(now);

            BookEntity book = bookRepository.findById((long) i).get();
            club.setBook(book);
            groupService.saveGroup(club);
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
