package com.shpota.blog.controller;

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
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "BlogController", urlPatterns = "/postInfo")
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

        resp.setContentType("text/html");
        String title = "Post from Postgresql Database";
        PrintWriter out = resp.getWriter();
        out.print("<html><body bgcolor=\"#f0f0f0\">");
        out.print("<h1 align=\"center\">" + title + "</h1>\n");
        showPostsInformation(out);
        out.print("</body></html>");
    }

    private void showPostsInformation(PrintWriter out) {
        JdbcBlogRepository repository = new JdbcBlogRepository(dataSource);
        List<Post> allPost = repository.getAllPost();
        for (Post post : allPost) {
            out.print("Post ID: " + post.getPostId() + "<br>");
            out.print("Post title: " + post.getTitle() + "<br>");
            out.print("Post date: " + post.getPostedDate() + "<br>");
            out.print("Post: " + post.getPostedText() + "<br>");
            out.print("*** *** ***");
        }
    }
}