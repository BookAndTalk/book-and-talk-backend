package book.talk.group.controller;

import book.talk.createbook.entity.BookEntity;
import book.talk.createbook.entity.BookRepository;
import book.talk.group.domain.Club;
import book.talk.group.domain.Groups;
import book.talk.group.model.GetGroupListRequest;
import book.talk.group.model.response.GetGroupListResponse;
import book.talk.group.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/groups")
public class GroupController {

    private final GroupService groupService;
    private final BookRepository bookRepository;
    //모임목록
//    @GetMapping
//    public List<Groups> list() {
//        return groupService.findGroups();
//    }





    // 모임 등록
    @PostMapping("/new")
    public String create(@RequestBody ClubForm form) {
        Club club = new Club();
        club.setName(form.getName());
        club.setLocation(form.getLocation());
        club.setGroupImage(form.getGroupImage());
        club.setBookTitle(form.getBookTitle());
        club.setMaxParticipants(form.getMaxParticipants());
        club.setStartDate(form.getStartDate());
        club.setDuration(form.getDuration());
        club.setGroupDescription(form.getGroupDescription());
        LocalDateTime now = LocalDateTime.now();
        club.setCreatedAt(now);

        BookEntity book = bookRepository.findById(form.getBookId()).get();
        club.setBook(book);
        groupService.saveGroup(club);
        return "group";
    }

    @GetMapping("/")
    public ResponseEntity<GetGroupListResponse> getGroupList(@RequestBody GetGroupListRequest request) {
        GetGroupListResponse response = groupService.getGroupList(request.getIsbn13());
        return ResponseEntity.ok(response);
    }

    // 수정 폼
//    @GetMapping("/{groupId}/edit")
//    public ClubForm updateGroupForm(@PathVariable("groupId") Long groupId) {
//        Club group = (Club) groupService.findOne(groupId);
//
//        ClubForm form = new ClubForm();
//        form.setId(group.getId());
//        form.setName(group.getName());
//        form.setLocation(group.getLocation());
//        form.setBookTitle(group.getBookTitle());
//        form.setMaxParticipants(group.getMaxParticipants());
//        form.setStartDate(group.getStartDate());
//        form.setDuration(group.getDuration());
//        form.setGroupDescription(group.getGroupDescription());
//
//        return form;
//    }
//
//    // 수정
//    @PutMapping("/{groupId}/edit")
//    public Club updateGroup(@PathVariable("groupId") Long groupId, @RequestBody ClubForm form) {
//        groupService.updateGroup(
//                groupId,
//                form.getName(),
//                form.getLocation(),
//                form.getBookTitle(),
//                form.getMaxParticipants(),
//                form.getStartDate(),
//                form.getDuration(),
//                form.getGroupDescription()
//        );
//        return (Club) groupService.findOne(groupId);
//    }
}
