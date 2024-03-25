import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import requests.AddCommentRequest;
import requests.GetPostInfoRequest;
import requests.GetPostsRequest;
import responses.AddCommentResponse;
import responses.GetPostInfoResponse;
import responses.GetPostsResponse;

import java.io.IOException;
import java.util.stream.Collectors;

@WebServlet("/comment")
public class CommentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        String body = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        GetPostInfoRequest getPostInfoRequest   = mapper.readValue(body, GetPostInfoRequest.class);
        GetPostInfoResponse getPostInfoResponse = DBManager.getDBManager().RetrievePostInfo(getPostInfoRequest);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        String body = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        AddCommentRequest addCommentRequest = mapper.readValue(body, AddCommentRequest.class);
//        AddCommentResponse addCommentResponse = DBManager.getDBManager().
    }
}
