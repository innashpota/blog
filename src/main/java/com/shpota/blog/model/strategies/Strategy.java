package com.shpota.blog.model.strategies;

import com.shpota.blog.model.BlogRepository;

import javax.servlet.http.HttpServletRequest;

public interface Strategy {
    String handle(HttpServletRequest req, BlogRepository repository);
}
