package requests;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import model.Comment;

@Getter @Setter
public class AddCommentRequest {

    private Comment comment;

    @JsonCreator
    public AddCommentRequest(@JsonProperty("comment") Comment comment) {
        this.comment = comment;
    }

    public String toJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(this);
    }
}
