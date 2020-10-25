package ir.maktab.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class MenuServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"en\">\n");
        out.println("<a href=\"checkDailyPosts\">Check Daily Posts</a>\" <br> ");
        out.println("<a href=\"follow.html\">To Follow Someone</a>\"");
        out.println("<a href=\"follower\">View Your Followings</a>\"");
        out.println("<a href=\"post.html\">To Post To Your Profile </a>\"");
        out.println("To See The Posts You Liked Press 5");
        out.println("To See Posts You Commented Press 6");
        out.println("To See Your Followings Press 7");
        out.println("To See Posts of Ones You Follow Press 8");
        out.println("To See Your Posts Press 9");
        out.println("To Edit Your Profile Press 10");
        out.println("To See Trends Press 11");
        out.println("To Edit Or Delete Your Posts Press 12");
        out.println("To UnFollow Someone Press 13");
        out.println("To Log Out Press 14");
    }

}
