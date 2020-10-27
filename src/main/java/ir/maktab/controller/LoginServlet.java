package ir.maktab.controller;

import ir.maktab.MyApp;
import ir.maktab.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet  extends HttpServlet {
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

        try {
            String userName = req.getParameter("username");
            String password = req.getParameter("password");
            out.println("<!DOCTYPE html>");
            out.println("<html lang=\"en\">\n");
            UserService userService = MyApp.getUserService();
            if (userService.login(userName, password)) {
                //req Dispatcher
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
