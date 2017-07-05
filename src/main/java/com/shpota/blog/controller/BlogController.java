package com.shpota.blog.controller;

import com.shpota.blog.model.BlogRepository;
import com.shpota.blog.model.StrategyDispatcher;
import com.shpota.blog.model.jdbc.JdbcBlogRepository;
import com.shpota.blog.model.strategies.RedirectPostsStrategy;
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
import java.time.format.DateTimeFormatter;

@WebServlet(name = "BlogController", urlPatterns = {"/", "/posts/*"})
public class BlogController extends HttpServlet {
    public final static DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm");
    private final static Logger LOGGER = Logger.getLogger(BlogController.class);
    private BlogRepository repository;
    private final StrategyDispatcher STRATEGY_DISPATCHER = StrategyDispatcher.construct();

    @Override
    public void init() throws ServletException {
        try {
            Context initContext = new InitialContext();
            DataSource dataSource = (DataSource) initContext.lookup(
                    "java:/comp/env/jdbc/postgres"
            );
            repository = new JdbcBlogRepository(dataSource);
            LOGGER.info("Using JDNI lookup got the DataSource: " + dataSource);
        } catch (NamingException e) {
            LOGGER.error("NamingException occurred in BlogController", e);
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.info("doGet method has been called");
        Strategy strategy = STRATEGY_DISPATCHER.jspName(req);
        String jsp = strategy.handle(req, repository);

        if (strategy.getClass().equals(RedirectPostsStrategy.class)) {
            resp.sendRedirect(jsp);
        } else {
            req.getRequestDispatcher(jsp).forward(req, resp);
        }
    }
}