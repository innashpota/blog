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
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Pattern;

@WebServlet(name = "BlogController", urlPatterns = {"/", "/posts/*"})
public class BlogController extends HttpServlet {
    public final static DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm");
    public final static String MESSAGE = "404 Page Not Found.";
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
        if ("/posts".equals(uri)) {
            List<Post> posts = getAllPosts();
            req.setAttribute("posts", posts);
            req.setAttribute("DATE_FORMATTER", DATE_FORMATTER);
            req.getRequestDispatcher("/posts.jsp").forward(req, resp);
        }
        if(pathInfo != null) {
            String postIdOnList = pathInfo.substring(1);
            Pattern pattern = Pattern.compile(".*[^0-9].*");
            if(!pattern.matcher(postIdOnList).matches()) {
                int postId = Integer.parseInt(postIdOnList);
                Post post = getPost(postId);
                if (post != null) {
                    req.setAttribute("post", post);
                    req.setAttribute("DATE_FORMATTER", DATE_FORMATTER);
                    req.getRequestDispatcher("/post.jsp").forward(req, resp);
                } else {
                    redirectError(req, resp, MESSAGE);
                }
            } else {
                redirectError(req, resp, MESSAGE);
            }
        }
    }

    private List<Post> getAllPosts() {
        BlogRepository repository = new JdbcBlogRepository(dataSource);
        return repository.getAllPost();
    }

    private Post getPost(int postId) {
        BlogRepository repository = new JdbcBlogRepository(dataSource);
        return repository.getPost(postId);
    }

    private void redirectError(HttpServletRequest req, HttpServletResponse resp, String message) throws ServletException, IOException {
        req.setAttribute("message", message);
        req.getRequestDispatcher("/error.jsp").forward(req, resp);
    }
}