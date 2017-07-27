package com.shpota.blog.model.strategies;

import com.shpota.blog.model.BlogRepository;
import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class CreatePostStrategyTest {
    @Test
    public void shouldHandle() throws Exception {
        BlogRepository repository = mock(BlogRepository.class);
        Strategy strategy = new CreatePostStrategy(repository);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        given(request.getRequestDispatcher("/create.jsp")).willReturn(dispatcher);

        strategy.handle(request, response);

        verify(dispatcher).forward(request, response);
    }
}