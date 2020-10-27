package ir.maktab.controller;

import ir.maktab.MyApp;
import ir.maktab.domains.Comment;
import ir.maktab.domains.Post;
import ir.maktab.services.CommentService;
import ir.maktab.services.PostService;
import ir.maktab.services.impl.PostServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class DeleteCommentServlet  extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CommentService commentService = MyApp.getCommentService();
        Post post = PostServiceImpl.getPost();
        try (ServletOutputStream out = resp.getOutputStream()) {
            out.println("<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>Liked Posts</title>\n"
                    + "    <style>\n");
            MyApp.displayPage(resp, out);
            int id = Integer.parseInt(req.getParameter("id"));
            id--;
            List<Comment> comments = post.getComments();
            Comment comment = comments.get(id);
            commentService.delete(comment);
            comments.remove(comment);
            post.setComments(comments);
            PostService postService = MyApp.getPostService();
            postService.saveOrUpdate(post);
        }
    }
}
