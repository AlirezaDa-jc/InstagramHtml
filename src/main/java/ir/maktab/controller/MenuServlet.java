package ir.maktab.controller;

import ir.maktab.MyApp;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MenuServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletOutputStream out = resp.getOutputStream();
        out.println("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Menu</title>\n"
                +"    <link rel=\"stylesheet\" href=\"Style.css\">"
                + "    <style>\n");
        MyApp.displayPage(resp, out);
        out.println("<div id=\"div2\">\n" +
                "<ul>");
        out.println("<li><a href=\"checkDailyPosts\">Check Daily Posts</a> <br></li> ");
        out.println("<li><a href=\"follow.html\">To Follow Someone</a><br></li>");
        out.println("<li><a href=\"follower\">View Your Followers</a><br></li>");
        out.println("<li><a href=\"followings\">View Your Followings</a><br></li>");
        out.println("<li><a href=\"post.html\">To Post To Your Profile </a><br></li>");
        out.println("<li><a href=\"postliked\">See The Posts You Liked </a><br></li>");
        out.println("<li><a href=\"postcommented\">See The Posts You Commented </a><br></li>");
        out.println("<li><a href=\"userfollowingpost\">See Posts of Ones You Follow  </a><br></li>");
        out.println("<li><a href=\"yourposts\"> See Your Posts </a><br></li>");
        out.println("<li><a href=\"trends\"> See Trends </a><br></li>");
        out.println("<li><a href=\"unfollow\"> Unfollow </a><br></li>");
        out.println("<li><a href=\"editProfile\"> Edit Profile </a><br></li>");
        out.println("<li><a href=\"editpost\"> Edit Posts </a><br></li>");
        out.println("<li><a href=\"logout\"> Log Out </a><br></li>");
        out.println("</body>\n" +
                "</html>");
        out.println("</ul>");
    }

}
