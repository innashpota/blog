package com.shpota.blog.model.strategies;

import com.shpota.blog.model.BlogRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class Strategy {
    final BlogRepository repository;

    Strategy(BlogRepository repository) {
        this.repository = repository;
    }

    public abstract void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
