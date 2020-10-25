package ir.maktab.controller;

import ir.maktab.MyApp;
import ir.maktab.domains.Post;
import ir.maktab.domains.User;
import ir.maktab.services.PostService;
import ir.maktab.services.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

public class DailyPosts extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletOutputStream out = resp.getOutputStream();
        resp.setContentType("text/html");
        AtomicInteger j = new AtomicInteger();
        AtomicInteger z = new AtomicInteger();
        j.set(0);
        z.set(0);
        /*
        Image Save !
        //TODO
         */
        Consumer<Post> displayPost = (c) -> {
            try {
                if (c.getImage() != null) {
                    j.getAndIncrement();
                    byte[] img = c.getImage();
                    File file = new File("D:\\Programs\\Java\\InstagramHTML\\src\\main\\webapp\\"+j+".jpg");
                    FileOutputStream fos = new FileOutputStream(file);
                    fos.write(img);
                    fos.close();
                    System.out.println(j.get());
                    System.out.println(c);
                    System.out.println(file.canRead());
                    System.out.println("<img src=\"http://localhost:8080/InstagramHTML_war_exploded/"+j+".jpg\" alt=\"output\">");
                    out.println("<img src=\"http://localhost:8080/InstagramHTML_war_exploded/"+j+".jpg\" alt=\"output\">");
                    out.println("<br>" + c + "<hr>");

                } else if (c.getVideo() != null) {
                    z.getAndIncrement();
                    byte[] video = c.getVideo();
                    File file = new File("D:\\Programs\\Java\\InstagramHTML\\src\\main\\webapp\\"+z+".mp4");
                    FileOutputStream fos = new FileOutputStream(file);
                    fos.write(video);
                    fos.close();
                    out.println("<video width=\"400\" controls>\n" +
                            "    <source src=\"http://localhost:8080/InstagramHTML_war_exploded/"+z+".mp4\" type=\"video\\mp4\">\n" +
                            "</video>");
                    out.println("<br>" + c + "<hr>");
                } else {
                    out.println("<br>" + c + "<hr>");
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        };
        User user = UserServiceImpl.getUser();
        PostService postService = MyApp.getPostService();
        List<List<Post>> dailyPosts = postService.getDailyPosts();
        AtomicInteger i = new AtomicInteger();
        dailyPosts.forEach(posts -> posts.stream()
                .filter((c) -> c.getDate().compareTo(user.getDate()) > 0)
                .forEach(displayPost.andThen((c) -> i.getAndIncrement())));
    }
}
