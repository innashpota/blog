package com.shpota.blog.model;

import com.shpota.blog.model.strategies.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RequestHandler {
    private final Map<Class<? extends Strategy>, Strategy> strategies;

    private RequestHandler(Map<Class<? extends Strategy>, Strategy> strategies) {
        this.strategies = strategies;
    }

    public static RequestHandler construct(BlogRepository repository) {
        Map<Class<? extends Strategy>, Strategy> strategies = new HashMap<>();
        strategies.put(RedirectAllPostsStrategy.class, new RedirectAllPostsStrategy(repository));
        strategies.put(AllPostsStrategy.class, new AllPostsStrategy(repository));
        strategies.put(PostStrategy.class, new PostStrategy(repository));
        strategies.put(ErrorStrategy.class, new ErrorStrategy(repository));
        strategies.put(RedirectCreatePostStrategy.class, new RedirectCreatePostStrategy(repository));
        strategies.put(CreatePostStrategy.class, new CreatePostStrategy(repository));
        strategies.put(AddPostStrategy.class, new AddPostStrategy(repository));
        strategies.put(DeletePostStrategy.class, new DeletePostStrategy(repository));
        strategies.put(EditPostStrategy.class, new EditPostStrategy(repository));
        strategies.put(SavePostStrategy.class, new SavePostStrategy(repository));
        return new RequestHandler(strategies);
    }

    public Strategy getStrategy(HttpServletRequest request) throws IOException {
        String uri = request.getRequestURI();
        if ("/".equals(uri)) {
            return strategies.get(RedirectAllPostsStrategy.class);
        }
        if ("/posts".equals(uri)) {
            if ("GET".equals(request.getMethod())) {
                if (request.getParameter("create") != null) {
                    return strategies.get(RedirectCreatePostStrategy.class);
                }
                return strategies.get(AllPostsStrategy.class);
            } else if ("POST".equals(request.getMethod())) {
                return strategies.get(AddPostStrategy.class);
            }
        }
        if (uri.matches("\\/posts\\/.*[0-9]")) {
            return strategies.get(PostStrategy.class);
        }
        if ("/posts/create".equals(uri)) {
            return strategies.get(CreatePostStrategy.class);
        }
        if (uri.matches("\\/posts\\/.*[0-9]\\/delete")) {
            return strategies.get(DeletePostStrategy.class);
        }
        if (uri.matches("\\/posts\\/.*[0-9]\\/edit")) {
            if ("GET".equals(request.getMethod())) {
                return strategies.get(EditPostStrategy.class);
            } else if ("POST".equals(request.getMethod())) {
                return strategies.get(SavePostStrategy.class);
            }
        }
        return strategies.get(ErrorStrategy.class);
    }
}