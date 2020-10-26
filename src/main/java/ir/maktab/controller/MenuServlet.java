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
        out.println("<a href=\"follower\">View Your Followers</a>\"");
        out.println("<a href=\"followings\">View Your Followings</a>\"");
        out.println("<a href=\"post.html\">To Post To Your Profile </a>\"");
        out.println("<a href=\"postliked\">See The Posts You Liked </a>\"");
        out.println("<a href=\"postcommented\">See The Posts You Commented </a>\"");
        out.println("<a href=\"userfollowingpost\">See Posts of Ones You Follow  </a>\"");
        out.println("<a href=\"yourposts\"> See Your Posts </a>\"");
        out.println("<a href=\"trends\"> See Trends </a>\"");
        out.println("<a href=\"unfollow\"> Unfollow </a>\"");
        out.println("To Edit Your Profile Press 10");
        out.println("To Edit Or Delete Your Posts Press 12");
        out.println("To Log Out Press 14");
    }

}
