package com.shpota.blog.model.strategies;

import com.shpota.blog.model.BlogRepository;
import com.shpota.blog.model.Post;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.shpota.blog.controller.BlogController.DATE_FORMATTER;

public class PostStrategy extends Strategy {
    public PostStrategy(BlogRepository repository) {
        super(repository);
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        String stringPostId = pathInfo.substring(1);
        int postId = Integer.parseInt(stringPostId);
        Post post = repository.getPost(postId);
        if (post != null) {
            request.setAttribute("post", post);
            request.setAttribute("DATE_FORMATTER", DATE_FORMATTER);
            request.getRequestDispatcher("/post.jsp").forward(request, response);
        } else {
            request.setAttribute("message", "Post with this id does not exist.");
            request.getRequestDispatcher("/error").forward(request, response);
        }
    }
}