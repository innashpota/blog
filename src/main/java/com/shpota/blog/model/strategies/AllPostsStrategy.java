package com.shpota.blog.model.strategies;

import com.shpota.blog.model.BlogRepository;
import com.shpota.blog.model.Post;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.shpota.blog.controller.BlogController.DATE_FORMATTER;

public class AllPostsStrategy implements Strategy {
    @Override
    public String handle(HttpServletRequest req, BlogRepository repository) {
        List<Post> posts = getAllPosts(repository);
        req.setAttribute("posts", posts);
        req.setAttribute("DATE_FORMATTER", DATE_FORMATTER);
        return "/posts.jsp";
    }

    private List<Post> getAllPosts(BlogRepository repository) {
        return repository.getAllPost();
    }
}
