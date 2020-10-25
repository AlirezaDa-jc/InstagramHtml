package ir.maktab;

import ir.maktab.base.services.BaseService;
import ir.maktab.base.services.impl.Service;
import ir.maktab.services.CommentService;
import ir.maktab.services.PostService;
import ir.maktab.services.UserService;
import ir.maktab.services.impl.CommentServiceImpl;
import ir.maktab.services.impl.PostServiceImpl;
import ir.maktab.services.impl.UserServiceImpl;

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
}
