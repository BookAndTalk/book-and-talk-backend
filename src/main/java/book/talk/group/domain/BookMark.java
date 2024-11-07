package book.talk.group.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Table(name = "bookMarks")
@Getter @Setter
public class BookMark {

    @Id
    @GeneratedValue
    @Column(name = "bookMark_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "group_id")
    private Groups groups;

    private String name;            //북클럽이름
    private String location;        //장소
    private String groupImage ;
    private String bookTitle;       //책 제목
    private int maxParticipants;       //최대참여인원
    private int participants;       //참여인원
    private LocalDate startDate;    //시작날짜
    private int duration;           //기간
    private String groupDescription; //북클럽소개

    @OneToMany(mappedBy = "bookMark", cascade = CascadeType.ALL)
    private List<BookMarkItem> bookMarkItems = new ArrayList<>();

    @Column(name = "bookMark_date", columnDefinition = "TIMESTAMP")
    private LocalDateTime bookMarkDate; // 생성시간

    @Enumerated(EnumType.STRING)
    private BookMarkStatus status;

    //==연관관계 메서드==//
    public void addBookMarkItem(BookMarkItem bookMarkItem) {
        bookMarkItems.add(bookMarkItem);
        bookMarkItem.setBookMark(this);
    }

    //==생성 메서드==//
    public static BookMark createBookMark(Groups groups, BookMarkItem... bookMarkItems) {
        BookMark bookMark = new BookMark();
        bookMark.setGroups(groups);
        bookMark.setStatus(BookMarkStatus.ACTIVE);
        bookMark.setBookMarkDate(LocalDateTime.now());

        for (BookMarkItem bookMarkItem : bookMarkItems) {
            bookMark.addBookMarkItem(bookMarkItem);
        }

        return bookMark;
    }
}
