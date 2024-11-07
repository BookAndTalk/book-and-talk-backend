package book.talk.group.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
public abstract class Groups {

    @Id @GeneratedValue
    @Column(name = "group_id")
    private Long id;

    private String name;            //북클럽이름
    private String location;        //장소
    private String groupImage ;
    private String bookTitle;       //책 제목
    private int maxParticipants;       //최대참여인원
    private int participants;       //참여인원
    private LocalDate startDate;    //시작날짜
    private int duration;           //기간
    private String groupDescription; //북클럽소개

    @ManyToMany(mappedBy = "groups")
    private List<Category> categories = new ArrayList<>();
}
