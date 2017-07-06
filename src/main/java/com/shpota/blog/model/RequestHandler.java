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
        strategies.put(RedirectPostsStrategy.class, new RedirectPostsStrategy(repository));
        strategies.put(AllPostsStrategy.class, new AllPostsStrategy(repository));
        strategies.put(PostStrategy.class, new PostStrategy(repository));
        strategies.put(ErrorStrategy.class, new ErrorStrategy(repository));
        return new RequestHandler(strategies);
    }

    public Strategy getStrategy(HttpServletRequest request) throws IOException {
        String uri = request.getRequestURI();
        if ("/".equals(uri)) {
            return strategies.get(RedirectPostsStrategy.class);
        }
        if ("/posts".equals(uri)) {
            return strategies.get(AllPostsStrategy.class);
        }
        if ("/error".equals(uri)) {
            return strategies.get(ErrorStrategy.class);
        }
        if (uri.matches("\\/posts\\/.*[0-9]")) {
            return strategies.get(PostStrategy.class);
        }
        return strategies.get(ErrorStrategy.class);
    }
}