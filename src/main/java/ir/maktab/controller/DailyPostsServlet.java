package ir.maktab.controller;

import ir.maktab.MyApp;
import ir.maktab.domains.Post;
import ir.maktab.domains.User;
import ir.maktab.services.PostService;
import ir.maktab.services.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class DailyPostsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletOutputStream out = resp.getOutputStream();
        resp.setContentType("text/html");
        User user = UserServiceImpl.getUser();
        PostService postService = MyApp.getPostService();
        List<List<Post>> dailyPosts = postService.getDailyPosts();
        dailyPosts.forEach(posts -> posts.stream()
                .filter((c) -> c.getDate().compareTo(user.getDate()) > 0)
                .forEach((c) -> postService.displayPost(c,out,true)));
    }
}
