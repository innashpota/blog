package com.shpota.blog.model.strategies;

import com.shpota.blog.model.BlogRepository;
import com.shpota.blog.model.Post;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.shpota.blog.controller.BlogController.DATE_FORMATTER;

public class AllPostsStrategy extends Strategy {
    public AllPostsStrategy(BlogRepository repository) {
        super(repository);
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Post> posts = repository.getAllPost();
        request.setAttribute("posts", posts);
        request.setAttribute("DATE_FORMATTER", DATE_FORMATTER);
        request.getRequestDispatcher("/posts.jsp").forward(request, response);
    }
}