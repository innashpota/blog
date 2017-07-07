package com.shpota.blog.model.strategies;

import com.shpota.blog.model.BlogRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ErrorStrategy extends Strategy {
    public ErrorStrategy(BlogRepository repository) {
        super(repository);
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/error.jsp").forward(request, response);
    }
}