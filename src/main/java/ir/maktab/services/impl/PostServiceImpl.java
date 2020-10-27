package ir.maktab.services.impl;

import ir.maktab.Dao.PostRepository;
import ir.maktab.Dao.impl.PostRepositoryImpl;
import ir.maktab.MyApp;
import ir.maktab.base.services.impl.BaseServiceImpl;
import ir.maktab.domains.Post;
import ir.maktab.domains.User;
import ir.maktab.services.CommentService;
import ir.maktab.services.PostService;
import ir.maktab.services.UserService;

import javax.servlet.ServletOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class PostServiceImpl extends BaseServiceImpl<Post, Long, PostRepository> implements PostService {
    //    private final Consumer<Post> addLikeOrComment;
//    private final Consumer<Post> displayPost;
    private final UserService userService;
    private final CommentService commentService;
    private User user;
    private static Post post;

    public PostServiceImpl() {
        PostRepository postRepository = new PostRepositoryImpl();
        userService = MyApp.getUserService();
        commentService = MyApp.getCommentService();
        user = UserServiceImpl.getUser();
        //Consumer!
//        displayPost = (c) -> {
//            if (c.getImage() != null) {
//                byte[] img = c.getImage();
//                File file = new File("output.jpg");
//                try {
//                    FileOutputStream fos = new FileOutputStream(file);
//                    fos.write(img);
//                    fos.close();
//                    Desktop desktop = Desktop.getDesktop();
//                    desktop.open(new File("output.jpg"));
//                    System.out.println(c);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            } else if (c.getVideo() != null) {
//                byte[] video = c.getVideo();
//                File file = new File("output.mp4");
//                try {
//                    FileOutputStream fos = new FileOutputStream(file);
//                    fos.write(video);
//                    fos.close();
//                    Desktop desktop = Desktop.getDesktop();
//                    desktop.open(new File("output.mp4"));
//                    System.out.println(c);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            } else {
//                System.out.println(c);
//            }
//        };
//        addLikeOrComment = (c) -> {
//            String choice = sc.getString("Comment Or Like Or Both Or Pass: ").toUpperCase();
//            switch (choice) {
//                case "LIKE":
//                    c.addLike(user);
//                    user.addPostLiked(c);
//                    break;
//                case "COMMENT":
//                    commentService.addCommentToPost(c, user);
//                    break;
//                case "BOTH":
//                    c.addLike(user);
//                    commentService.addCommentToPost(c, user);
//                    break;
//                default:
//            }
//            if (!choice.equals("PASS")) {
//                baseRepository.saveOrUpdate(c);
//            }
//
//        };
        super.setRepository(postRepository);
    }

    public static Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        PostServiceImpl.post = post;
    }

    @Override
    public boolean save(String content) {
        Post post = new Post();
        Date date = new Date();
        post.setDate(date);
        user = UserServiceImpl.getUser();
        try {
//            if (path != null) {
//                File file = new File(path);
//                byte[] bFile = new byte[(int) file.length()];
//                try {
//                    FileInputStream fileInputStream = new FileInputStream(file);
//                    fileInputStream.read(bFile);
//                    fileInputStream.close();
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                    return false;
//                }
//                String pathFile = path.toUpperCase();
//                if (pathFile.endsWith(".JPG")) {
//                    post.setImage(bFile);
//                } else if (pathFile.endsWith("MP4")) {
//                    post.setVideo(bFile);
//                }
//            }
            post.setContent(content);
            UserServiceImpl.getUser().addPost(post);
            post.setUser(UserServiceImpl.getUser());
            baseRepository.saveOrUpdate(post);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public Set<Post> getLikedPosts() {
        User u = UserServiceImpl.getUser();
        return u.getPostsLiked();

    }


    @Override
    public List<Post> getTrends() {
        int max = baseRepository.findMaximumLike();
        return baseRepository.findTrends(max);

    }

    @Override
    public void edit(ServletOutputStream out, String... fields) {
        List<Post> all = UserServiceImpl.getUser().getPosts();
        all.forEach((c) -> displayPost(c, out, false));
        int id = Integer.parseInt(fields[0]);
        id--;
        try {
            Post post = all.get(id);
            if (post == null) return;
            if (!(fields[1] == null || fields[1].equals("null"))) {
                updateContent(post, fields[1]);
            }
            if (!(fields[2] == null || fields[2].equals("null"))) {
                deleteComment(post, out);
            }
            if (!(fields[3] == null || fields[3].equals("null"))) {
                baseRepository.delete(post);
                baseRepository.resetCache();
                all.remove(post);
            }
            UserServiceImpl.getUser().setPosts(all);
        } catch (IndexOutOfBoundsException | IOException ex) {
//            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }

    }

    private void deleteComment(Post post, ServletOutputStream out) throws IOException {
        out.println("<form name=\"form1\" method=\"post\" action=\"deletecomment\" target=\"_blank\">\n");
        post.getComments().forEach((c) -> {
            try {
                out.println(String.valueOf(c));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        out.println("    <label for=\"id\">ID:</label><input type=\"text\" id=\"id\" name=\"id\">\n" +
                "    <input type=\"submit\" value=\"submit\">\n" +
                "</form>");
        PostServiceImpl.post = post;
//        List<Comment> comments = post.getComments();
//        comments.forEach(System.out::println);
//        int id = Integer.parseInt(sc.getString("Comment ID:"));
//        id--;
//        try {
//        Comment comment = comments.get(id);
//        commentService.delete(comment);
//        comments.remove(comment);
//        post.setComments(comments);
//        baseRepository.saveOrUpdate(post);
//        } catch (Exception ex) {
//            System.out.println("Invalid ID!");
//        }
    }

    private void updateContent(Post post, String content) {
        post.setContent(content);
        saveOrUpdate(post);
    }

    @Override
    public List<List<Post>> getDailyPosts() {
        User user = UserServiceImpl.getUser();
        Set<User> followings = user.getFollowings();
        return followings.stream()
                .map(User::getPosts)
                .collect(Collectors.toList());
    }


    @Override
    public List<Post> getCommentedPosts() {
        return commentService.getCommentedPosts();
    }

    @Override
    public void displayPost(Post c, ServletOutputStream out, boolean flag) {
//        AtomicInteger j = new AtomicInteger();
//        AtomicInteger z = new AtomicInteger();
//        j.set(0);
//        z.set(0);
        try {
//            if (c.getImage() != null) {
//                j.getAndIncrement();
//                byte[] img = c.getImage();
//                File file = new File("D:\\Programs\\Java\\InstagramHTML\\src\\main\\webapp\\" + j + ".jpg");
//                FileOutputStream fos = new FileOutputStream(file);
//                fos.write(img);
//                fos.close();
//                System.out.println(j.get());
//                System.out.println(c);
//                System.out.println(file.canRead());
//                System.out.println("<img src=\"http://localhost:8080/InstagramHTML_war_exploded/" + j + ".jpg\" alt=\"output\">");
//                out.println("<img src=\"http://localhost:8080/InstagramHTML_war_exploded/" + j + ".jpg\" alt=\"output\">");
//                out.println("<br>" + c + "<hr>");
//
//            } else if (c.getVideo() != null) {
//                z.getAndIncrement();
//                byte[] video = c.getVideo();
//                File file = new File("D:\\Programs\\Java\\InstagramHTML\\src\\main\\webapp\\" + z + ".mp4");
//                FileOutputStream fos = new FileOutputStream(file);
//                fos.write(video);
//                fos.close();
//                out.println("<video width=\"400\" controls>\n" +
//                        "    <source src=\"http://localhost:8080/InstagramHTML_war_exploded/" + z + ".mp4\" type=\"video\\mp4\">\n" +
//                        "</video>");
//                out.println("<br>" + c + "<hr>");
//            } else {
            out.println("<br>" + c + "<br>");
//            }
            if (flag) {
                out.println("<form name=\"form1\" method=\"post\" action=\"commentorlike\" target=\"_blank\">\n" +
                        "    <br>\n" +
                        "    <label for=\"Like\">Like:</label><input type=\"checkbox\" id=\"Like\" name=\"Like\" checked>\n" +
                        "    <label for=\"Comment\">Comment:</label><input type=\"checkbox\" id=\"Comment\" name=\"Comment\">\n" +
                        "    <label for=\"CommentText\">Comment:</label>\n" +
                        "    <input type=\"text\" id=\"CommentText\" name=\"CommentText\">\n" +
                        "    <input type=\"submit\" value=\"submit\">\n" +
                        "</form>");
                post = c;
            }
            out.println("<hr>");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
