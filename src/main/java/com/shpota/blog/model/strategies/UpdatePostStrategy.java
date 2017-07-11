package com.shpota.blog.model.strategies;

import com.shpota.blog.model.BlogRepository;
import com.shpota.blog.model.Post;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.OffsetDateTime;

public class UpdatePostStrategy extends Strategy {
    public UpdatePostStrategy(BlogRepository repository) {
        super(repository);
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        updatePost(request);
        response.sendRedirect("/posts/" + getPostId(request));
    }

    private void updatePost(HttpServletRequest request) {
        Post post = new Post(
                request.getParameter("title"),
                OffsetDateTime.now(),
                request.getParameter("context")
        );
        repository.updatePost(getPostId(request), post);
    }

    private int getPostId(HttpServletRequest request) {
        String uri = request.getRequestURI();
        String postIdString = uri.replace("/posts/", "")
                .replace("/edit", "");
        return Integer.parseInt(postIdString);
    }
}