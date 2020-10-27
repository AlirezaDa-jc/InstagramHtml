package ir.maktab.controller;

import ir.maktab.MyApp;
import ir.maktab.domains.User;
import ir.maktab.services.PostService;
import ir.maktab.services.UserService;
import ir.maktab.services.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

public class FollowingUserPostServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletOutputStream out = resp.getOutputStream();
        out.println("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Liked Posts</title>\n"
                + "    <style>\n" );
        MyApp.displayPage(resp, out);
        User user = UserServiceImpl.getUser();
        Set<User> followings = user.getFollowings();
        followings.forEach((c)-> {
            try {
                out.println(String.valueOf(c));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        out.println("<form name=\"form1\" method=\"post\" action=\"userfollowingpost\">\n" +
                "    <label for=\"UserName\">UserName:</label>\n" +
                "    <input type=\"text\" id=\"UserName\" name=\"username\">\n" +
                "    <input type=\"submit\" value=\"submit\">\n" +
                "</form>");
        out.println("<a href=\"menu\">Menu!</a>");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("username");
        UserService userService = MyApp.getUserService();
        PostService postService = MyApp.getPostService();
        ServletOutputStream out = resp.getOutputStream();
        MyApp.displayPage(resp, out);
        User user = userService.findByUserName(userName);
        if(user != null) {
            user.getPosts()
                    .forEach((c) -> postService.displayPost(c, out, true));
        }else{
            out.println("<h1>Invalid User!</h1>");
        }
    }
}
