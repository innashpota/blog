package com.shpota.blog.model.strategies;

import com.shpota.blog.model.BlogRepository;
import com.shpota.blog.model.Post;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditPostStrategy extends Strategy {
    public EditPostStrategy(BlogRepository repository) {
        super(repository);
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int postId = extractId(request);
        Post post = repository.getPost(postId);
        request.setAttribute("post", post);
        request.getRequestDispatcher("/edit.jsp").forward(request, response);
    }

    private int extractId(HttpServletRequest request) {
        String uri = request.getRequestURI();
        String postIdString = uri.replace("/posts/", "")
                .replace("/edit", "");
        return Integer.parseInt(postIdString);
    }
}