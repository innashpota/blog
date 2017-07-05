package com.shpota.blog.model.strategies;

import com.shpota.blog.model.BlogRepository;
import com.shpota.blog.model.Post;

import javax.servlet.http.HttpServletRequest;

import static com.shpota.blog.controller.BlogController.DATE_FORMATTER;

public class PostStrategy implements Strategy {
    @Override
    public String handle(HttpServletRequest req, BlogRepository repository) {
        String pathInfo = req.getPathInfo();
        String stringPostId = pathInfo.substring(1);
        String message;
        if (stringPostId.matches(".*[0-9]")) {
            int postId = Integer.parseInt(stringPostId);
            Post post = getPost(repository, postId);
            if (post != null) {
                req.setAttribute("post", post);
                req.setAttribute("DATE_FORMATTER", DATE_FORMATTER);
                return "/post.jsp";
            } else {
                message = "Post with this id does not exist.";
            }
        } else {
            message = "You have entered an incorrect post id.";
        }
        ErrorStrategy errorStrategy = new ErrorStrategy(message);
        return errorStrategy.handle(req, repository);
    }

    private Post getPost(BlogRepository repository, int postId) {
        return repository.getPost(postId);
    }
}
