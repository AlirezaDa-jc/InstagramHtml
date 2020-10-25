package ir.maktab.controller;

import ir.maktab.MyApp;
import ir.maktab.services.PostService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class PostServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        String content = req.getParameter("content");
        String path = req.getParameter("path").toLowerCase();
        if(!(path.endsWith("jpg") || path.endsWith("mp4"))){
            path = null;
        }
        PostService postService = MyApp.getPostService();
        if (postService.save(content, path)) {
            out.println("Post Successfully Added");
        }else {
            out.println("The Post Is Not Added ! Error Occurred");
        }
        out.println("<a href=\"menu\">Menu!</a>");

    }
}
