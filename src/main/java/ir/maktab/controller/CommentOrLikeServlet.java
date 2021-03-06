package ir.maktab.controller;

import ir.maktab.MyApp;
import ir.maktab.domains.Post;
import ir.maktab.domains.User;
import ir.maktab.services.CommentService;
import ir.maktab.services.impl.PostServiceImpl;
import ir.maktab.services.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommentOrLikeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String like = req.getParameter("Like");
        String comment = req.getParameter("Comment");
        User user = UserServiceImpl.getUser();
        Post post = PostServiceImpl.getPost();
        try (ServletOutputStream out = resp.getOutputStream()) {
            out.println("<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>Comment Or Like</title>\n"
                    + "    <style>\n");
            MyApp.displayPage(resp, out);
            if (like != null && !like.equals("null")) {
                post.addLike(user);
                user.addPostLiked(post);
            }
            if (comment != null && !comment.equals("null")) {
                CommentService commentService = MyApp.getCommentService();
                String commentText = req.getParameter("CommentText");
                commentService.addCommentToPost(post, user, commentText);
            }
        }
    }
}
