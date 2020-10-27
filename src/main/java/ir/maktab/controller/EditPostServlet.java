package ir.maktab.controller;

import ir.maktab.MyApp;
import ir.maktab.domains.User;
import ir.maktab.services.PostService;
import ir.maktab.services.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditPostServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = UserServiceImpl.getUser();
        PostService postService = MyApp.getPostService();
        ServletOutputStream out = resp.getOutputStream();
        out.println("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Liked Posts</title>\n"
                + "    <style>\n");
        MyApp.displayPage(resp, out);
        user.getPosts().forEach((c) -> postService.displayPost(c, out, false));
        out.println(
                "<form method=\"post\" action=\"editpost\" target=\"_self\">\n" +
                        "    <label for=\"id\"><br>ID:</label><input type=\"text\" id=\"id\" name=\"id\"><br><br>\n" +
                        "    <label for=\"content\"><br>Content:</label><input type=\"text\" id=\"content\" name=\"content\"><br><br>\n" +
                        "    <label for=\"DeleteComment\">Delete a Comment:</label><br>\n" +
                        "    <input type=\"checkbox\" id=\"Delete a Comment\" name=\"DeleteComment\"><br><br>\n" +
                        "    <label for=\"DeletePost\">Delete Post:</label><br>\n" +
                        "    <input type=\"checkbox\" id=\"DeletePost\" name=\"DeletePost\"><br><br>\n" +
                        "    <input type=\"submit\" value=\"submit\">\n" +
                        "</form>\n" +
                        "</body>\n" +
                        "</html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String content = req.getParameter("content");
        String deleteComment = req.getParameter("DeleteComment");
        String deletePost = req.getParameter("DeletePost");
        PostService postService = MyApp.getPostService();
        ServletOutputStream out = resp.getOutputStream();
        out.println("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>EditProfile</title>\n" +
                "</head>\n" +
                "<body>\n");
        postService.edit(out, id, content, deleteComment, deletePost);
        out.println("</body>\n" +
                "</html>");
    }
}
