import model.Comment;
import model.Post;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import requests.AddCommentRequest;
import requests.AddPostRequest;
import requests.GetPostInfoRequest;
import responses.AddCommentResponse;
import responses.AddPostResponse;
import responses.GetPostInfoResponse;
import responses.GetPostsResponse;

import java.util.List;

public class DBManager {
    private static DBManager dBManager;
    private final EntityManagerFactory emf;
    private final EntityManager em;
    private final CriteriaBuilder cb;

    private DBManager(){
        emf = Persistence.createEntityManagerFactory("AnonBook-unit");
        em = emf.createEntityManager();
        cb = em.getCriteriaBuilder();
    }
    public static DBManager getDBManager() {
        if(dBManager == null){
            dBManager = new DBManager();
        }
        return dBManager;
    }

    public void savePost(Post post){
        Post attachedPost = em.merge(post);
        em.persist(attachedPost);
        em.getTransaction().commit();
    }

    public AddPostResponse savePost(AddPostRequest addPostRequest){
        Post attachedPost = em.merge(addPostRequest.getPost());
        em.persist(attachedPost);
        em.getTransaction().commit();

        return new AddPostResponse();
    }

    public void saveComment(Comment comment){
        Comment attachedComment = em.merge(comment);
        em.persist(attachedComment);
        em.getTransaction().commit();
    }
    public GetPostInfoResponse RetrievePostInfo(GetPostInfoRequest getPostInfoRequest){
        CriteriaQuery<Post> cq = cb.createQuery(Post.class);
        Root<Post> root = cq.from(Post.class);
        cq.select(root).where(cb.equal(root.get("post_id"), getPostInfoRequest.getView_post_id()));
        TypedQuery<Post> typedQuery = em.createQuery(cq);
        List<Post> list = typedQuery.getResultList();
        Post post = list.get(0);
        return new GetPostInfoResponse(post);
    }
    public GetPostsResponse retrievePosts(){
        CriteriaQuery<Post> cq = cb.createQuery(Post.class);
        Root<Post> root = cq.from(Post.class);
        cq.select(root);
        TypedQuery<Post> typedQuery = em.createQuery(cq);
        List<Post> list = typedQuery.getResultList();

        return new GetPostsResponse(list);
    }


}
