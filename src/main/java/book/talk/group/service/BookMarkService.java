package book.talk.group.service;

import book.talk.group.domain.*;
import book.talk.group.domain.Groups;
import book.talk.group.repository.GroupRepository;
import book.talk.group.repository.BookMarkRepository;
import book.talk.group.repository.BookMarkSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BookMarkService {

    private final BookMarkRepository bookMarkRepository;
    private final GroupRepository groupRepository;

    @Transactional
    public BookMark createBookMark(Long groupId) {
        if (groupId == null) {
            throw new IllegalArgumentException("모임을 선택해야 합니다.");
        }

        Groups group = groupRepository.findOne(groupId);
        if (group == null) {
            throw new IllegalArgumentException("존재하지 않는 모임입니다.");
        }

        BookMark bookMark = new BookMark();
        bookMark.setGroups(group);
        // 추가적인 정보를 설정
        bookMark.setName(group.getName());
        bookMark.setLocation(group.getLocation());
        bookMark.setBookTitle(group.getBookTitle());
        bookMark.setMaxParticipants(group.getMaxParticipants());
        bookMark.setStartDate(group.getStartDate());
        bookMark.setDuration(group.getDuration());
        bookMark.setGroupDescription(group.getGroupDescription());

//        bookMark.setStatus(BookMarkStatus.ACTIVE);

        bookMarkRepository.save(bookMark);
        return bookMark;
    }

    public List<BookMark> findBookMark(BookMarkSearch bookMarkSearch) {
        return bookMarkRepository.findAllByCriteria(bookMarkSearch);
    }

    public BookMark findBookMarkById(Long bookMarkId) {
        return bookMarkRepository.findOne(bookMarkId);
    }

    @Transactional
    public void cancelBookMark(Long bookMarkId) {
        BookMark bookMark = bookMarkRepository.findOne(bookMarkId);
        if (bookMark == null) {
            throw new IllegalArgumentException("존재하지 않는 북마크입니다.");
        }
//        bookMark.setStatus(BookMarkStatus.CANCEL);
    }
}
