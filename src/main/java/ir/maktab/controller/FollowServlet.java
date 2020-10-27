package ir.maktab.controller;

import ir.maktab.MyApp;
import ir.maktab.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FollowServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletOutputStream out = resp.getOutputStream();
        out.println("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Liked Posts</title>\n"
                + "    <style>\n" );
        MyApp.displayPage(resp, out);
        String userName = req.getParameter("username");
        UserService userService = MyApp.getUserService();
        if(userService.follow(userName)){
            out.println("You Are Now Following " + userName + " !");
        }else{
            out.println("Wrong User!");
            out.println("<a href=\"follow.html\">Try Again!</a>");
        }
        out.println("<a href=\"menu\">Menu!</a>");
    }
}
