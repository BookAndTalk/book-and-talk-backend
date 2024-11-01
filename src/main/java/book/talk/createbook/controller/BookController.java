package book.talk.createbook.controller;

import book.talk.createbook.service.BookCreateService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {
    private final BookCreateService bookGroupService;

    public BookController(BookCreateService bookGroupService) {
        this.bookGroupService = bookGroupService;
    }

    @PostMapping("api/create-groups")
    public String createGroup(@RequestParam("bookId") Long bookId, @RequestParam("thumbnail") String thumbnail, @RequestParam("bookTitle") String bookTitle) {
        bookGroupService.createOrUpdateBook(bookId, thumbnail, bookTitle);
        return "Group create or update successfully";
    }
}
