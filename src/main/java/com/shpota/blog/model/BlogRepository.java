package com.shpota.blog.model;

import java.util.List;

public interface BlogRepository {
    int addPost(Post post);

    void deletePost(int postId);

    Post getPost(int postId);

    void updatePost(int postId, Post post);

    List<Post> getAllPost();
}