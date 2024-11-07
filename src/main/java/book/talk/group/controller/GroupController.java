package book.talk.group.controller;

import book.talk.group.domain.Club;
import book.talk.group.domain.Groups;
import book.talk.group.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/groups")
public class GroupController {

    private final GroupService groupService;

    //모임목록
    @GetMapping
    public List<Groups> list() {
        return groupService.findGroups();
    }





    // 모임 등록
    @PostMapping("/new")
    public String create(@RequestBody ClubForm form) {
        Club club = new Club();
        club.setName(form.getName());
        club.setLocation(form.getLocation());
        club.setBookTitle(form.getBookTitle());
        club.setMaxParticipants(form.getMaxParticipants());
        club.setParticipants(form.getParticipants());
        club.setStartDate(form.getStartDate());
        club.setDuration(form.getDuration());
        club.setGroupDescription(form.getGroupDescription());

        groupService.saveGroup(club);
        return "group";
    }

    // 수정 폼
    @GetMapping("/{groupId}/edit")
    public ClubForm updateGroupForm(@PathVariable("groupId") Long groupId) {
        Club group = (Club) groupService.findOne(groupId);

        ClubForm form = new ClubForm();
        form.setId(group.getId());
        form.setName(group.getName());
        form.setLocation(group.getLocation());
        form.setBookTitle(group.getBookTitle());
        form.setParticipants(group.getParticipants());
        form.setStartDate(group.getStartDate());
        form.setDuration(group.getDuration());
        form.setGroupDescription(group.getGroupDescription());

        return form;
    }

    // 수정
    @PutMapping("/{groupId}/edit")
    public Club updateGroup(@PathVariable("groupId") Long groupId, @RequestBody ClubForm form) {
        groupService.updateGroup(
                groupId,
                form.getName(),
                form.getLocation(),
                form.getBookTitle(),
                form.getParticipants(),
                form.getStartDate(),
                form.getDuration(),
                form.getGroupDescription()
        );
        return (Club) groupService.findOne(groupId);
    }
}
