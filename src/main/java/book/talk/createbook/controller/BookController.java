package book.talk.createbook.controller;

import book.talk.createbook.entity.BookEntity;
import book.talk.createbook.model.BookRequest;
import book.talk.createbook.service.BookCreateService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {
    private final BookCreateService bookCreateService;

    public BookController(BookCreateService bookCreateService) {
        this.bookCreateService = bookCreateService;
    }

    @PostMapping("api/create-book")
    public String createBook(@RequestBody BookRequest request) {
        // 서비스 메서드를 호출하여 데이터 저장
        bookCreateService.createOrUpdateBook(request.getIsbn13(),request.getThumbnail(), request.getBookTitle());
        return "Group create or update successfully";
    }
}
