package ir.maktab.controller;

import ir.maktab.MyApp;
import ir.maktab.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class EditProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>EditProfile</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<form method=\"post\" action=\"editProfile\" target=\"_self\">\n" +
                "    <label for=\"name\">Name:</label><input type=\"text\" id=\"name\" name=\"name\">\n" +
                "    <label for=\"username\">UserName:</label><input type=\"text\" id=\"username\" name=\"userName\">\n" +
                "    <label for=\"currentPassword\">Current Password:</label><input type=\"text\" id=\"currentPassword\" name=\"currentPassword\">\n" +
                "    <label for=\"password\">Password:</label><input type=\"text\" id=\"password\" name=\"password\">\n" +
                "    <label for=\"confirmationPassword\">Confirm Password:</label><input type=\"text\" id=\"confirmationPassword\" name=\"confirmationPassword\">\n" +
                "    <input type=\"submit\" value=\"submit\">\n" +
                "</form>\n" +
                "</body>\n" +
                "</html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String userName = req.getParameter("userName");
        String currentPassword = req.getParameter("currentPassword");
        String password = req.getParameter("password");
        String confirmationPassword = req.getParameter("confirmationPassword");
        UserService userService = MyApp.getUserService();
        ServletOutputStream out = resp.getOutputStream();
        out.println("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Liked Posts</title>\n"
                + "    <style>\n" );
        MyApp.displayPage(resp, out);
        userService.edit(out, name, userName, currentPassword, password, confirmationPassword);
        out.println("</body>\n" +
                "</html>");

    }
}
