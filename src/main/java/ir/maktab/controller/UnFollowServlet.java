package ir.maktab.controller;

import ir.maktab.MyApp;
import ir.maktab.domains.User;
import ir.maktab.services.UserService;
import ir.maktab.services.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

public class UnFollowServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        ServletOutputStream out = resp.getOutputStream();
        out.println("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Menu</title>\n"
                + "    <style>\n" );
        MyApp.displayPage(resp,out);
        User user = UserServiceImpl.getUser();
        Set<User> followings = user.getFollowings();
        followings.forEach((c)-> {
            try {
                out.println(String.valueOf(c));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        out.println("<form name=\"form1\" method=\"post\" action=\"unfollow\">\n" +
                "    <label for=\"UserName\">UserName:</label><br>\n" +
                "    <input type=\"text\" id=\"UserName\" name=\"username\"><br><br>\n" +
                "    <input type=\"submit\" value=\"submit\">\n" +
                "</form>");
        out.println("<a href=\"menu\">Menu!</a>");
        out.println("</body>\n" +
                "</html>");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("username");
        UserService userService = MyApp.getUserService();
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");
        User user = userService.findByUserName(userName);
        if (user != null) {
            userService.unFollow(user);
        } else {
            out.println("<h1>Invalid User!</h1>");
        }
    }
}
