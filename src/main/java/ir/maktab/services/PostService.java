package ir.maktab.services;

import ir.maktab.base.services.BaseService;
import ir.maktab.domains.Post;

import javax.servlet.ServletOutputStream;
import java.util.List;
import java.util.Set;

public interface PostService extends BaseService<Post, Long> {
    boolean save(String content,String path);

    Set<Post> getLikedPosts();

    List<List<Post>> getDailyPosts();

    void displayUsersPosts();

    void displayFollowingsPosts();

    List<Post> getTrends();

    void edit();

    List<Post> getCommentedPosts();

    void displayPost(Post c, ServletOutputStream out);
}
