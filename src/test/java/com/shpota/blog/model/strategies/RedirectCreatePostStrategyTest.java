package com.shpota.blog.model.strategies;

import com.shpota.blog.model.BlogRepository;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class RedirectCreatePostStrategyTest {
    @Test
    public void shouldHandle() throws Exception {
        BlogRepository repository = mock(BlogRepository.class);
        Strategy strategy = new RedirectCreatePostStrategy(repository);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        strategy.handle(request, response);

        verify(response).sendRedirect("/posts/create");
    }
}