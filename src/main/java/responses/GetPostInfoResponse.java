package responses;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import model.Post;

@AllArgsConstructor
@Getter @Setter
public class GetPostInfoResponse {
    private Post post;

}
