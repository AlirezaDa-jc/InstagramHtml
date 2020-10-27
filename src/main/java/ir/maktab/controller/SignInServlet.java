package ir.maktab.controller;

import ir.maktab.MyApp;
import ir.maktab.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignInServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String userName = req.getParameter("username");
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        ServletOutputStream out = resp.getOutputStream();
        out.println("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Sign In</title>\n"
                + "    <style>\n" );
        MyApp.displayPage(resp,out);
        UserService userService = MyApp.getUserService();
        if(userService.signIn(userName,name,password)){
            out.println("Welcome" + userName);
            out.println("<a href=\"menu\">Menu!</a>");
        }else{
            out.println("User Already Registered!");
            out.println("<a href=\"login.html\">Login!</a><br><br>");
            out.println("<a href=\"signin.html\">Sign In!!</a>");
        }
        out.println("</body>\n" +
                "</html>");
    }

}
