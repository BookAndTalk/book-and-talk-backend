package book.talk.group.service;

import book.talk.createbook.entity.BookEntity;
import book.talk.createbook.entity.BookRepository;
import book.talk.group.domain.Club;
import book.talk.group.domain.Groups;
import book.talk.group.model.response.GetGroupListResponse;
import book.talk.group.model.response.GroupDto;
import book.talk.group.repository.GroupRepository;
import book.talk.group.repository.GroupsRepository;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;
    private final BookRepository bookRepository;
    private final GroupsRepository groupsRepository;

    @Transactional
    public void saveGroup(Groups group) {
        groupRepository.save(group);
    }

    @Transactional
    public Groups updateGroup(Long groupId, String name, String location, String bookTitle,
                              int maxParticipants, LocalDate startDate, int duration, String groupDescription) {
        Groups group = groupRepository.findOne(groupId);
        if (group == null) {
            throw new IllegalArgumentException("존재하지 않는 그룹입니다.");
        }

        group.setName(name);
        group.setLocation(location);
        group.setBookTitle(bookTitle);
        group.setMaxParticipants(maxParticipants);
        group.setStartDate(startDate);
        group.setDuration(duration);
        group.setGroupDescription(groupDescription);

        return group;
    }

    public GetGroupListResponse getGroupList(Long isbn13) {
        Optional<BookEntity> bookOptional = bookRepository.findByIsbn13(isbn13);
        bookOptional.get();

        BookEntity book = bookOptional.get();
        List<Club> groupList = groupsRepository.findByBook(book);

        int totalCount = (int) groupList.stream()
                .filter(group -> "모집중".equals(group.getStatus()))
                .count();

        List<GroupDto> groupDtoList = groupList.stream()
                .map(group -> groupToGroupDto(group))
                .collect(Collectors.toList());

        GetGroupListResponse response = new GetGroupListResponse();
        response.setTotalCount(totalCount);
        response.setData(groupDtoList);

        return response;
    }

    private GroupDto groupToGroupDto(Club group) {
        GroupDto groupDto = new GroupDto();
        groupDto.setGroupId(group.getId());
        groupDto.setName(group.getName());
        groupDto.setParticipants(group.getParticipants());
        groupDto.setMaxParticipants(group.getMaxParticipants());
        groupDto.setStartDate(group.getStartDate());
        groupDto.setDuration(group.getDuration());
        groupDto.setCreatedAt(group.getCreatedAt());
        groupDto.setStatus(group.getStatus());
        groupDto.setBookId(group.getBook().getBookId());
        groupDto.setLocation(group.getLocation());
        groupDto.setGroupImage(group.getGroupImage());

        return groupDto;
    }

    public List<Groups> findGroups() {
        return groupRepository.findAll();
    }

    public Groups findOne(Long groupId) {
        return groupRepository.findOne(groupId);
    }
}
