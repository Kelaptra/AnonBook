import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import model.Post;
import requests.AddPostRequest;
import requests.GetPostsRequest;
import responses.AddPostResponse;
import responses.GetPostsResponse;

import java.io.File;
import java.io.IOException;
import java.util.stream.Collectors;

@WebServlet("/post")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class PostServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        String body = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        GetPostsRequest getPostsRequest   = mapper.readValue(body, GetPostsRequest.class);
        GetPostsResponse getPostsResponse = DBManager.getDBManager().retrievePosts();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String post_content = req.getParameter("post_content");
        Part imagePart = req.getPart("image");
        String imageURL = ImagesManager.getImagesManager().getImageURL(imagePart);
        Post post = new Post(post_content, imageURL);

        DBManager.getDBManager().savePost(post);

        String response = "<img src='" + imageURL + "' alt='Uploaded Image'>";
        resp.setContentType("text/html");
        resp.getWriter().write(response);


//        ObjectMapper mapper = new ObjectMapper();
//        String body = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
//        AddPostRequest addPostRequest   = mapper.readValue(body, AddPostRequest.class);
//        AddPostResponse addPostResponse = DBManager.getDBManager().retrievePosts();
    }
}
