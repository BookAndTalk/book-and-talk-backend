package book.talk.group.model.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetGroupListResponse {
    private int totalCount;
    private List<GroupDto> data;
}
