package com.shpota.blog.model.strategies;

import com.shpota.blog.model.BlogRepository;

import javax.servlet.http.HttpServletRequest;

public class ErrorStrategy implements Strategy {
    private final String message;

    public ErrorStrategy(String message) {
        this.message = message;
    }

    @Override
    public String handle(HttpServletRequest req, BlogRepository repository) {
        req.setAttribute("message", message);
        return "/error.jsp";
    }
}
