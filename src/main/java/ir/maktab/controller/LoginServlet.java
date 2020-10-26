package ir.maktab.controller;

import ir.maktab.MyApp;
import ir.maktab.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet  extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        try {
            String userName = req.getParameter("username");
            String password = req.getParameter("password");
            out.println("<!DOCTYPE html>");
            out.println("<html lang=\"en\">\n");
            UserService userService = MyApp.getUserService();
            if (userService.login(userName, password)) {
                out.println("Welcome" + userName);
                out.println("<a href=\"menu\">Menu!</a>");
            } else {
                out.println("Wrong Password");
                out.println("<a href=\"login.html\">Try Again!</a>");

            }
        }catch (NullPointerException ex){
            out.println("<a href=\"login.html\">Try Again!</a>");
        }
    }
}
