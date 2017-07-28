package com.shpota.blog.model;

import com.shpota.blog.model.strategies.*;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class RequestHandlerTest {
    @Test
    public void shouldToGetStrategyGivenRedirectAllPosts() throws Exception {
        checkStrategy("/", "GET", null, RedirectAllPostsStrategy.class);
    }

    @Test
    public void shouldToGetStrategyGivenAllPostsStrategy() throws Exception {
        checkStrategy("/posts", "GET", null, AllPostsStrategy.class);
    }

    @Test
    public void shouldToGetStrategyGivenRedirectCreatePost() throws Exception {
        checkStrategy("/posts", "GET", "Create", RedirectCreatePostStrategy.class);
    }

    @Test
    public void shouldToGetStrategyGivenAddPost() throws Exception {
        checkStrategy("/posts", "POST", null, AddPostStrategy.class);
    }

    @Test
    public void shouldToGetStrategyGivenPost() throws Exception {
        checkStrategy("/posts/4", "GET", null, PostStrategy.class);
    }

    @Test
    public void shouldToGetStrategyGivenCreatePost() throws Exception {
        checkStrategy("/posts/create", "GET", null, CreatePostStrategy.class);
    }

    @Test
    public void shouldToGetStrategyGivenDeletePost() throws Exception {
        checkStrategy("/posts/4/delete", "GET", null, DeletePostStrategy.class);
    }

    @Test
    public void shouldToGetStrategyGivenEditPost() throws Exception {
        checkStrategy("/posts/4/edit", "GET", null, EditPostStrategy.class);
    }

    @Test
    public void shouldToGetStrategyGivenSavePost() throws Exception {
        checkStrategy("/posts/4/edit", "POST", null, SavePostStrategy.class);
    }

    private void checkStrategy(String uri, String method, String parameterValue, Class strategyClass) throws IOException {
        BlogRepository repository = mock(BlogRepository.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        RequestHandler requestHandler = RequestHandler.construct(repository);
        given(request.getRequestURI()).willReturn(uri);
        given(request.getMethod()).willReturn(method);
        given(request.getParameter("create")).willReturn(parameterValue);

        Strategy strategy = requestHandler.getStrategy(request);

        assertEquals(strategyClass, strategy.getClass());
    }
}