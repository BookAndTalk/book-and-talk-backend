package book.talk.group.controller;

import book.talk.group.domain.BookMark;
import book.talk.group.domain.Groups;
import book.talk.group.repository.BookMarkSearch;
import book.talk.group.service.GroupService;
import book.talk.group.service.BookMarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/bookmarks")
public class BookMarkController {

    private final BookMarkService bookMarkService;
    private final GroupService groupService;

    @GetMapping("/groups")
    public List<Groups> getGroups() {
        return groupService.findGroups();
    }

    @PostMapping("/new")
    public BookMark createBookMark(@RequestParam("groupId") Long groupId) {
        if (groupId == null) {
            throw new IllegalArgumentException("모임을 선택해야 합니다.");
        }
        return bookMarkService.createBookMark(groupId);
    }

    @GetMapping
    public List<BookMark> getBookMarks(@ModelAttribute BookMarkSearch bookMarkSearch) {
        return bookMarkService.findBookMark(bookMarkSearch);
    }

    @PostMapping("/{bookMarkId}/cancel")
    public String cancelBookMark(@PathVariable Long bookMarkId) {
        bookMarkService.cancelBookMark(bookMarkId);
        return "BookMark canceled";
    }
}
