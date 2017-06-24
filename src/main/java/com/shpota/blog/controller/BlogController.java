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

@WebServlet(name = "BlogController", urlPatterns = {"/", "/posts/*"})
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
        String pathInfo = req.getPathInfo();
        if ("/".equals(uri)) {
            resp.sendRedirect("/posts");
        }
        List<Post> posts = getAllPosts();
        if ("/posts".equals(uri)) {
            req.setAttribute("posts", posts);
            req.getRequestDispatcher("/posts.jsp").forward(req, resp);
        }
        if(pathInfo != null) {
            String positionOnList = pathInfo.substring(1);
            try {
                int position = Integer.parseInt(positionOnList);
                if (position < 0 || position > posts.size()) {
                    getError404(req, resp);
                } else {
                    Post post = posts.get(position);
                    req.setAttribute("post", post);
                    req.getRequestDispatcher("/post.jsp").forward(req, resp);
                }
            } catch (NumberFormatException ignored) {
                getError404(req, resp);
            }
        }
    }

    private List<Post> getAllPosts() {
        BlogRepository repository = new JdbcBlogRepository(dataSource);
        return repository.getAllPost();
    }

    private void getError404(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/error404.jsp").forward(req, resp);
    }
}