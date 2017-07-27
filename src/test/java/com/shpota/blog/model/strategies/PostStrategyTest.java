package com.shpota.blog.model.strategies;

import com.shpota.blog.model.BlogRepository;
import com.shpota.blog.model.Post;
import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.OffsetDateTime;

import static com.shpota.blog.model.strategies.Strategy.DATE_FORMATTER;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class PostStrategyTest {
    @Test
    public void shouldHandle() throws Exception {
        BlogRepository repository = mock(BlogRepository.class);
        Strategy strategy = new PostStrategy(repository);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        int postId = 4;
        given(request.getPathInfo()).willReturn("/" + postId);
        Post post = new Post("Title", OffsetDateTime.now(), "Posted text");
        given(repository.getPost(postId)).willReturn(post);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        given(request.getRequestDispatcher("/post.jsp")).willReturn(dispatcher);

        strategy.handle(request, response);

        verify(request).setAttribute("post", post);
        verify(request).setAttribute("formatter", DATE_FORMATTER);
        verify(dispatcher).forward(request, response);
    }

    @Test
    public void shouldRedirectToHandle() throws Exception {
        BlogRepository repository = mock(BlogRepository.class);
        Strategy strategy = new PostStrategy(repository);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        int postId = 4;
        given(request.getPathInfo()).willReturn("/" + postId);
        given(repository.getPost(postId)).willReturn(null);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        given(request.getRequestDispatcher("/error")).willReturn(dispatcher);

        strategy.handle(request, response);

        verify(request).setAttribute("message", "Post with this id does not exist.");
        verify(dispatcher).forward(request, response);
    }
}