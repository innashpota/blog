package com.shpota.blog.model.strategies;

import com.shpota.blog.model.BlogRepository;
import com.shpota.blog.model.Post;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.OffsetDateTime;

public class AddStrategy extends Strategy{
    public AddStrategy(BlogRepository repository) {
        super(repository);
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int newPostId = addPost(request);
        response.sendRedirect("/posts/" + newPostId);
    }

    private int addPost(HttpServletRequest request) {
        String title = request.getParameter("title");
        String postedText = request.getParameter("context");
        Post post = new Post(title, OffsetDateTime.now(), postedText);
        return repository.addPost(post);
    }
}