package book.talk.group.controller;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class
ClubForm {

    private Long id;

    private String name;            //북클럽이름
    private String location;        //장소
    private String groupImage ;
    private String bookTitle;       //책 제목
    private int maxParticipants;    //최대참여인원
    private int participants;       //참여인원
    private LocalDate startDate;    //시작날짜
    private int duration;           //기간
    private String groupDescription; //북클럽소개



}
