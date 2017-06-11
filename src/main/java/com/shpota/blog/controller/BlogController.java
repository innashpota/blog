package com.shpota.blog.controller;

import com.shpota.blog.model.BlogRepository;
import com.shpota.blog.model.Post;
import com.shpota.blog.model.jdbc.JdbcBlogRepository;
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
import java.util.List;

@WebServlet(name = "BlogController", urlPatterns = {"/posts", "/posts/"})
public class BlogController extends HttpServlet {
    private final static Logger LOGGER = Logger.getLogger(BlogController.class);
    private DataSource dataSource;

    @Override
    public void init() throws ServletException {
        try {
            Context initContext = new InitialContext();
            dataSource = (DataSource) initContext.lookup(
                    "java:/comp/env/jdbc/postgres"
            );
            LOGGER.info("Using JDNI lookup got the DataSource: " + dataSource);
        } catch (NamingException e) {
            LOGGER.error("NamingException occurred in BlogController", e);
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.info("doGet method has been called");
        String uri = req.getRequestURI();
        if ("/posts".equals(uri)) {
            List<Post> posts = getAllPosts();
            req.setAttribute("posts", posts);
            req.getRequestDispatcher("/posts.jsp").forward(req, resp);
        }
    }

    private List<Post> getAllPosts() {
        BlogRepository repository = new JdbcBlogRepository(dataSource);
        return repository.getAllPost();
    }
}