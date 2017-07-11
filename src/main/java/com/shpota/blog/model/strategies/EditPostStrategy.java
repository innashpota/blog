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
        String uri = request.getRequestURI();
        String postIdString = uri.replace("/posts/", "")
                .replace("/edit", "");
        int postId = Integer.parseInt(postIdString);
        Post post = repository.getPost(postId);
        request.setAttribute("post", post);
        request.getRequestDispatcher("/edit.jsp").forward(request, response);
    }
}