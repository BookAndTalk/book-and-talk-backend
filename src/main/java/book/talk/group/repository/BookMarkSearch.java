package book.talk.group.repository;

import book.talk.group.domain.BookMarkStatus;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BookMarkSearch {

    private BookMarkStatus bookMarkStatus; //주문상태[ORDER, CANCEL]
}
