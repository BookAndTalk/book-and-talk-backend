package book.talk.createbook.service;

import book.talk.createbook.entity.BookEntity;
import book.talk.createbook.entity.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class BookCreateService {
    private final BookRepository repository;

    public BookCreateService(BookRepository repository) {
        this.repository = repository;
    }

    public void createOrUpdateBook(int isbn13, String thumbnail, String bookTitle) {
        // 1. bookId로 책ID가 있는지 확인
        BookEntity book = repository.findByIsbn13(isbn13).orElse(null);

        // 2. 책 정보가 없으면 새로 BookEntity객체를 만들고, GroupCount 1로 설정
        if (book == null) {
            book = new BookEntity();
            book.setThumbnail(thumbnail);
            book.setBookTitle(bookTitle);
            book.setIsbn13(isbn13);
            book.setGroupCount(1); // 새로운 책이므로 초기값 1로 설정
        } else {
            // 3. 책 정보가 있으면 GroupCount 1 증가
            book.setGroupCount(book.getGroupCount() + 1);
        }

        // 4. 변경된 정보(새로운 책이든 횟수가 증가된 책이든)를 저장
        repository.save(book);
    }

}
