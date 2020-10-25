package ir.maktab.controller;

import ir.maktab.MyApp;
import ir.maktab.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class FollowServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String userName = req.getParameter("username");
        PrintWriter out = resp.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"en\">\n");
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
