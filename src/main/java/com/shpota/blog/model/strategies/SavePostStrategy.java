package com.shpota.blog.model.strategies;

import com.shpota.blog.model.BlogRepository;
import com.shpota.blog.model.Post;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SavePostStrategy extends Strategy {
    public SavePostStrategy(BlogRepository repository) {
        super(repository);
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        String postIdString = uri.replace("/posts/", "")
                .replace("/edit", "");
        int postId = Integer.parseInt(postIdString);
        updatePost(request, postId);
        response.sendRedirect("/posts/" + postId);
    }

    private void updatePost(HttpServletRequest request, int postId) {
        Post post = repository.getPost(postId);
        Post newPost = new Post(
                request.getParameter("title"),
                post.getPostedDate(),
                request.getParameter("context")
        );
        repository.updatePost(postId, newPost);
    }
}