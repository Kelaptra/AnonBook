package responses;

import model.Post;
import java.util.List;

public class GetPostsResponse {
    private Post[] posts;

    public GetPostsResponse(Post[] posts) {
        this.posts = posts;
    }
    public GetPostsResponse(List<Post> list) {
        this.posts = list.toArray(new Post[list.size()]);
    }

}
