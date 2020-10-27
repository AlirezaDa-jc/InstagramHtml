package ir.maktab.controller;

import ir.maktab.MyApp;
import ir.maktab.services.PostService;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PostServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String content = req.getParameter("content");


        PostService postService = MyApp.getPostService();
        ServletOutputStream out = resp.getOutputStream();
        out.println("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Post</title>\n"
                + "    <style>\n" );
        MyApp.displayPage(resp, out);
        if (postService.save(content)) {
            out.println("Post Successfully Added");
        }else {
            out.println("The Post Is Not Added ! Error Occurred");
        }
        out.println("<a href=\"menu\">Menu!</a>");
        out.println("</body>\n" +
                "</html>");

    }
}
