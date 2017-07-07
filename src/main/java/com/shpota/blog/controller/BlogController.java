package com.shpota.blog.controller;

import com.shpota.blog.model.BlogRepository;
import com.shpota.blog.model.RequestHandler;
import com.shpota.blog.model.jdbc.JdbcBlogRepository;
import com.shpota.blog.model.strategies.Strategy;
import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;

@WebServlet(name = "BlogController", urlPatterns = {"", "/posts/*", "/error"})
public class BlogController extends HttpServlet {
    private final static Logger LOGGER = Logger.getLogger(BlogController.class);
    private RequestHandler requestHandler;

    @Override
    public void init() throws ServletException {
        try {
            Context initContext = new InitialContext();
            DataSource dataSource = (DataSource) initContext.lookup(
                    "java:/comp/env/jdbc/postgres"
            );
            BlogRepository repository = new JdbcBlogRepository(dataSource);
            requestHandler = RequestHandler.construct(repository);
            LOGGER.info("Using JDNI lookup got the DataSource: " + dataSource);
        } catch (NamingException e) {
            LOGGER.error("NamingException occurred in BlogController", e);
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info("doGet method has been called");
        Strategy strategy = requestHandler.getStrategy(request);
        strategy.handle(request, response);
    }
}