package com.shpota.blog.model.strategies;

import com.shpota.blog.model.BlogRepository;

import javax.servlet.http.HttpServletRequest;

public class RedirectPostsStrategy implements Strategy {
    @Override
    public String handle(HttpServletRequest req, BlogRepository repository) {
        return "/posts";
    }
}