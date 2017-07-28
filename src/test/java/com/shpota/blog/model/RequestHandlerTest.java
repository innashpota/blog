package com.shpota.blog.model;

import com.shpota.blog.model.strategies.*;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@RunWith(JUnitParamsRunner.class)
public class RequestHandlerTest {
    @Test
    @Parameters
    public void shouldToGetStrategy(
            String uri, String method, String parameterValue, Class strategyClass
    ) throws Exception {
        BlogRepository repository = mock(BlogRepository.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        RequestHandler requestHandler = RequestHandler.construct(repository);
        given(request.getRequestURI()).willReturn(uri);
        given(request.getMethod()).willReturn(method);
        given(request.getParameter("create")).willReturn(parameterValue);

        Strategy strategy = requestHandler.getStrategy(request);

        assertEquals(strategyClass, strategy.getClass());
    }

    private Object[] parametersForShouldToGetStrategy() {
        return new Object[]{
                new Object[]{"/", "GET", null, RedirectAllPostsStrategy.class},
                new Object[]{"/posts", "GET", null, AllPostsStrategy.class},
                new Object[]{"/posts", "GET", "Create", RedirectCreatePostStrategy.class},
                new Object[]{"/posts", "POST", null, AddPostStrategy.class},
                new Object[]{"/posts/4", "GET", null, PostStrategy.class},
                new Object[]{"/posts/create", "GET", null, CreatePostStrategy.class},
                new Object[]{"/posts/4/delete", "GET", null, DeletePostStrategy.class},
                new Object[]{"/posts/4/edit", "GET", null, EditPostStrategy.class},
                new Object[]{"/posts/4/edit", "POST", null, SavePostStrategy.class}
        };
    }
}