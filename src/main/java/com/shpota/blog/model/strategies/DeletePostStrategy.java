package com.shpota.blog.model.strategies;

import com.shpota.blog.model.BlogRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeletePostStrategy extends Strategy {
    public DeletePostStrategy(BlogRepository repository) {
        super(repository);
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int postId = extractId(request);
        repository.deletePost(postId);
        response.sendRedirect("/posts");
    }

    private int extractId(HttpServletRequest request) {
        String uri = request.getRequestURI();
        String postIdString = uri.replace("/posts/", "")
                .replace("/delete", "");
        return Integer.parseInt(postIdString);
    }
}