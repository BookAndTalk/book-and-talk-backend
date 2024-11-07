package book.talk.group.service;

import book.talk.group.domain.Groups;
import book.talk.group.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;

    @Transactional
    public void saveGroup(Groups group) {
        groupRepository.save(group);
    }

    @Transactional
    public Groups updateGroup(Long groupId, String name, String location, String bookTitle,
                             int participants, LocalDate startDate, int duration, String groupDescription) {
        Groups group = groupRepository.findOne(groupId);
        if (group == null) {
            throw new IllegalArgumentException("존재하지 않는 그룹입니다.");
        }

        group.setName(name);
        group.setLocation(location);
        group.setBookTitle(bookTitle);
        group.setParticipants(participants);
        group.setStartDate(startDate);
        group.setDuration(duration);
        group.setGroupDescription(groupDescription);

        return group;
    }

    public List<Groups> findGroups() {
        return groupRepository.findAll();
    }

    public Groups findOne(Long groupId) {
        return groupRepository.findOne(groupId);
    }
}
