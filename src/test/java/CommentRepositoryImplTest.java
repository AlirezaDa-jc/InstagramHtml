import ir.maktab.Dao.CommentRepository;
import ir.maktab.Dao.PostRepository;
import ir.maktab.Dao.UserRepository;
import ir.maktab.Dao.impl.CommentRepositoryImpl;
import ir.maktab.Dao.impl.PostRepositoryImpl;
import ir.maktab.Dao.impl.UserRepositoryImpl;
import ir.maktab.base.repository.impl.BaseRepositoryImpl;
import ir.maktab.domains.Comment;
import ir.maktab.domains.Post;
import ir.maktab.domains.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Date;

class CommentRepositoryImplTest extends BaseRepositoryImpl<Comment,Long> {
    private static PostRepository postRepository;
    private static User user;
    private static Post firstPost;
    private static UserRepository userRepository;
    private static CommentRepository commentRepository;
    static Comment comment;

    @BeforeAll
    static void beforeAll() {
        postRepository = new PostRepositoryImpl();
        user = new User();
        user.setUserName("Test");
        user.setPassword("test");
        user.setName("test");
        userRepository = new UserRepositoryImpl();
        userRepository.saveOrUpdate(user);
        firstPost = new Post();
        firstPost.setContent("content Post First");
        firstPost.setUser(user);
        firstPost.setDate(new Date());
        postRepository.saveOrUpdate(firstPost);
        commentRepository = new CommentRepositoryImpl();
        comment = new Comment();
        comment.setComment("comment");
        comment.setUser(user);
        comment.setPost(firstPost);
        postRepository.saveOrUpdate(firstPost);
    }

    @Test
    void findByUser() {
        System.out.println(user);
        System.out.println(comment);
        System.out.println(firstPost);
        Assertions.assertEquals(firstPost,commentRepository.findByUser(user).get(0));
    }

    @Override
    protected Class<Comment> getEntityClass() {
        return Comment.class;
    }
    @AfterAll
    static void afterAll() {
        userRepository.delete(user);
        postRepository.delete(firstPost);
    }
}