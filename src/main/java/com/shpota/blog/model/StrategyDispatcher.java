package com.shpota.blog.model;

import com.shpota.blog.model.strategies.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StrategyDispatcher {
    private final List<Strategy> strategies;

    private StrategyDispatcher(List<Strategy> strategies) {
        this.strategies = strategies;
    }

    public static StrategyDispatcher construct() {
        RedirectPostsStrategy redirectPostsStrategy = new RedirectPostsStrategy();
        AllPostsStrategy allPostsStrategy = new AllPostsStrategy();
        PostStrategy postStrategy = new PostStrategy();
        ErrorStrategy errorStrategy = new ErrorStrategy("The reason is not clear. Contact an administrator.");
        List<Strategy> strategies = new ArrayList<>();
        strategies.add(0, redirectPostsStrategy);
        strategies.add(1, allPostsStrategy);
        strategies.add(2, postStrategy);
        strategies.add(3, errorStrategy);
        return new StrategyDispatcher(strategies);
    }

    public Strategy jspName(HttpServletRequest req) throws IOException {
        String uri = req.getRequestURI();
        String pathInfo = req.getPathInfo();
        if ("/".equals(uri)) {
            return strategies.get(0);
        }
        if ("/posts".equals(uri)) {
            return strategies.get(1);
        }
        if (pathInfo != null) {
            return strategies.get(2);
        }
        return strategies.get(3);
    }
}
