package requests;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GetPostInfoRequest {

    private int view_post_id;

    @JsonCreator
    public GetPostInfoRequest(@JsonProperty("view_post_id") int view_post_id) {
        this.view_post_id = view_post_id;
    }

    public String toJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(this);
    }
}
