package book.talk.group.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class GroupDto {
    private Long groupId;
    private String name;
    private int participants;
    private int maxParticipants;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    private int duration;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;

    private String status; // "모집중", "모집완료"
    private Long bookId;
    private String location;
    private String groupImage;
}
