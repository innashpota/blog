package com.shpota.blog.model.strategies;

import com.shpota.blog.model.BlogRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

public abstract class Strategy {
    final static DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm");
    final BlogRepository repository;

    Strategy(BlogRepository repository) {
        this.repository = repository;
    }

    public abstract void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}