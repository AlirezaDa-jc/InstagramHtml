package ir.maktab.controller;

import ir.maktab.MyApp;
import ir.maktab.services.PostService;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TrendingPostsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletOutputStream out = resp.getOutputStream();
        resp.setContentType("text/html");
        PostService postService = MyApp.getPostService();
        postService.getTrends()
                .forEach((c) -> postService.displayPost(c,out));
    }
}
