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
import java.util.Set;

public class PostLikedServlet  extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (ServletOutputStream out = resp.getOutputStream()) {
            out.println("<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>Liked Posts</title>\n"
                    + "    <style>\n" );
            MyApp.displayPage(resp, out);
            PostService postService = MyApp.getPostService();
            Set<Post> likedPosts = postService.getLikedPosts();
            if (likedPosts != null) {
                likedPosts.forEach((c) -> postService.displayPost(c, out, true));
            } else {
                out.println("<h3>No Posts Liked!</h3>");
            }
            out.println("</body>\n" +
                    "</html>");
        }
    }

}
