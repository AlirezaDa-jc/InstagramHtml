package ir.maktab;

import ir.maktab.services.CommentService;
import ir.maktab.services.PostService;
import ir.maktab.services.UserService;
import ir.maktab.services.impl.CommentServiceImpl;
import ir.maktab.services.impl.PostServiceImpl;
import ir.maktab.services.impl.UserServiceImpl;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyApp {

    private MyApp(){}

    private static UserService userService = new UserServiceImpl();
    private static CommentService commentService = new CommentServiceImpl();
    private static PostService postService = new PostServiceImpl();

    public static void setUserService() {
        userService = new UserServiceImpl();
    }

    public static UserService getUserService(){
            return userService;
    }

    public static void setPostService() {
        postService=new PostServiceImpl();
    }

    public static void setCommentService() {
        commentService = new CommentServiceImpl();
    }

    public static CommentService getCommentService() {
        return commentService;
    }

    public static PostService getPostService() {
        return postService;
    }
    public static void displayPage(HttpServletResponse resp, ServletOutputStream out) throws IOException {
        resp.setContentType("text/html");
        out.println(
                "        body{\n" +
                "            background-color: black;\n" +
                "            color: wheat;\n" +
                "        }\n" +
                "        h1{\n" +
                "            margin: 0;\n" +
                "        }\n" +
                "        form{\n" +
                "            display: table;\n" +
                "            margin: 0 auto;\n" +
                "        }\n" +
                "        html {\n" +
                "            width: 100%;\n" +
                "            height: 100%;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>" + "<div style=\"color: white ; font-family: 'Times New Roman',serif;padding: 5px;margin: 0\">\n" +
                "    <h1 style=\"padding-bottom: 0;margin-bottom: 0;margin-top:0;margin-left: 10px;\">Instagram</h1>\n" +
                "</div>");
    }
}
