package ir.maktab.services;

import ir.maktab.base.services.BaseService;
import ir.maktab.domains.Post;

import java.util.List;

public interface PostService extends BaseService<Post, Long> {
    boolean save(String content,String path);

    void displayLikedPosts();

    List<List<Post>> getDailyPosts();

    void displayUsersPosts();

    void displayFollowingsPosts();

    void displayTrends();

    void edit();

    void displayCommentedPosts();
}
