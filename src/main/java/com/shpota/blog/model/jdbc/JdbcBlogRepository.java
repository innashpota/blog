package com.shpota.blog.model.jdbc;

import com.shpota.blog.model.BlogRepository;
import com.shpota.blog.model.Post;
import com.shpota.blog.model.exception.RepositoryException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class JdbcBlogRepository implements BlogRepository {
    private static final String SQL_INSERT_POST =
            "INSERT INTO posts (title, posted_date, posted_text) " +
                    "VALUES (?, ?, ?);";
    private static final String SQL_DELETE_POST =
            "DELETE FROM posts " +
                    "WHERE post_id = ?;";
    private static final String SQL_SELECT_POST =
            "SELECT title, posted_date, posted_text " +
                    "FROM posts " +
                    "WHERE post_id = ?;";
    private static final String SQL_UPDATE_POST =
            "UPDATE posts " +
                    "SET title = ?, posted_date = ?, posted_text = ?" +
                    "WHERE post_id = ?;";
    private static final String SQL_SELECT_ALL_POSTS =
            "SELECT * " +
                    "FROM posts " +
                    "ORDER BY post_id DESC;";
    private final DataSource dataSource;

    public JdbcBlogRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public int addPost(Post post) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement addStatement = connection.prepareStatement(
                     SQL_INSERT_POST,
                     RETURN_GENERATED_KEYS
             )) {
            addStatement.setString(1, post.getTitle());
            addStatement.setObject(2, post.getPostedDate());
            addStatement.setString(3, post.getPostedText());
            addStatement.executeUpdate();
            try (ResultSet resultSet = addStatement.getGeneratedKeys()) {
                int id = -1;
                if (resultSet.next()) {
                    id = resultSet.getInt("post_id");
                }
                return id;
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public void deletePost(int postId) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement deleteStatement = connection.prepareStatement(
                     SQL_DELETE_POST
             )) {
            deleteStatement.setInt(1, postId);
            deleteStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public Post getPost(int postId) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement selectStatement = connection.prepareStatement(
                     SQL_SELECT_POST
             )) {
            selectStatement.setInt(1, postId);
            try (ResultSet resultSet = selectStatement.executeQuery()) {
                Post post = null;
                if (resultSet.next()) {
                    post = new Post(
                            postId,
                            resultSet.getString("title"),
                            resultSet.getObject(
                                    "posted_date",
                                    OffsetDateTime.class
                            ),
                            resultSet.getString("posted_text")
                    );
                }
                return post;
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public void updatePost(int postId, Post post) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement updateStatement = connection.prepareStatement(
                     SQL_UPDATE_POST
             )) {
            updateStatement.setString(1, post.getTitle());
            updateStatement.setObject(2, post.getPostedDate());
            updateStatement.setString(3, post.getPostedText());
            updateStatement.setInt(4, postId);
            updateStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public List<Post> getAllPost() {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement allUsersStatement = connection.prepareStatement(
                     SQL_SELECT_ALL_POSTS
             )) {
            try (ResultSet resultSet = allUsersStatement.executeQuery()) {
                List<Post> postsList = new ArrayList<>();
                while (resultSet.next()) {
                    postsList.add(new Post(
                            resultSet.getInt("post_id"),
                            resultSet.getString("title"),
                            resultSet.getObject(
                                    "posted_date",
                                    OffsetDateTime.class
                            ),
                            resultSet.getString("posted_text")
                    ));
                }
                return postsList;
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
    }
}
