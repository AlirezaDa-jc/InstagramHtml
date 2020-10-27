package ir.maktab.controller;

import ir.maktab.MyApp;
import ir.maktab.domains.Post;
import ir.maktab.services.PostService;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class PostCommentedServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletOutputStream out = resp.getOutputStream();
        out.println("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Commented Posts</title>\n"
                + "    <style>\n" );
        MyApp.displayPage(resp, out);
        PostService postService = MyApp.getPostService();
        List<Post> commentedPosts = postService.getCommentedPosts();
        commentedPosts.forEach((c)->postService.displayPost(c,out, true));
        out.println("</body>\n" +
                "</html>");
    }
}
