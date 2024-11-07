package book.talk.group.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BookMarkItem {

    @Id @GeneratedValue
    @Column(name = "bookMark_item_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "group_id")
    private Groups group;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "bookMarks_id")
    private BookMark bookMark;

    public BookMarkItem(Groups group) {
        this.group = group;
    }

    public static BookMarkItem createBookMarkItem(Groups group) {
        BookMarkItem bookMarkItem = new BookMarkItem();
        bookMarkItem.setGroup(group);

        return bookMarkItem;
    }
}
