package ir.maktab.controller;

import ir.maktab.domains.User;
import ir.maktab.services.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

public class FollowingsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        Set<User> followings = UserServiceImpl.getUser().getFollowings();
        followings.forEach(out::println);
        out.println("<a href=\"menu\">Menu!</a>");
    }
}
