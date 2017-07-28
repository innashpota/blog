package com.shpota.blog.model;

import com.shpota.blog.model.strategies.AllPostsStrategy;
import com.shpota.blog.model.strategies.RedirectAllPostsStrategy;
import com.shpota.blog.model.strategies.RedirectCreatePostStrategy;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class RequestHandlerTest {
    @Test
    public void shouldToGetStrategyGivenRedirectAllPosts() throws Exception {
        BlogRepository repository = mock(BlogRepository.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        RequestHandler requestHandler = RequestHandler.construct(repository);
        given(request.getRequestURI()).willReturn("/");

        assertEquals(RedirectAllPostsStrategy.class, requestHandler.getStrategy(request).getClass());
    }

    @Test
    public void shouldToGetStrategyGivenAllPostsStrategy() throws Exception {
        BlogRepository repository = mock(BlogRepository.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        RequestHandler requestHandler = RequestHandler.construct(repository);
        given(request.getRequestURI()).willReturn("/posts");
        given(request.getMethod()).willReturn("GET");
        given(request.getParameter("create")).willReturn(null);

        assertEquals(AllPostsStrategy.class, requestHandler.getStrategy(request).getClass());
    }

    @Test
    public void shouldToGetStrategyGivenRedirectCreatePost() throws Exception {
        BlogRepository repository = mock(BlogRepository.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        RequestHandler requestHandler = RequestHandler.construct(repository);
        given(request.getRequestURI()).willReturn("/posts");
        given(request.getMethod()).willReturn("GET");
        given(request.getParameter("create")).willReturn("Create new post");

        assertEquals(RedirectCreatePostStrategy.class, requestHandler.getStrategy(request).getClass());
    }
}