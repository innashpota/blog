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

@WebServlet(name = "BlogController", urlPatterns = {"/", "/posts/*"})
public class BlogController extends HttpServlet {
    public final static DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm");
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
            String stringPostId = pathInfo.substring(1);
            if(stringPostId.matches(".*[0-9]")) {
                int postId = Integer.parseInt(stringPostId);
                Post post = getPost(postId);
                if (post != null) {
                    req.setAttribute("post", post);
                    req.setAttribute("DATE_FORMATTER", DATE_FORMATTER);
                    req.getRequestDispatcher("/post.jsp").forward(req, resp);
                } else {
                    redirectToErrorPage(req, resp, "Post with this id does not exist.");
                }
            } else {
                redirectToErrorPage(req, resp, "You have entered an incorrect post id.");
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

    private void redirectToErrorPage(HttpServletRequest req, HttpServletResponse resp, String message) throws ServletException, IOException {
        req.setAttribute("message", message);
        req.getRequestDispatcher("/error.jsp").forward(req, resp);
    }
}