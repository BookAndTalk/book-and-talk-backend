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
    private int participants = 1;       //참여인원 (1 고정)
    private String status = "모집중";  //모집 상태
    private Long hostId = 3L;       //호스트 id
    private LocalDate startDate;    //시작날짜
    private int duration;           //기간
    private String groupDescription; //북클럽소개




}
