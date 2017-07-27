package com.shpota.blog.model.strategies;

import com.shpota.blog.model.BlogRepository;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class DeletePostStrategyTest {
    @Test
    public void shouldHandle() throws Exception {
        BlogRepository repository = mock(BlogRepository.class);
        Strategy strategy = new DeletePostStrategy(repository);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        int postId = 4;
        given(request.getRequestURI()).willReturn("/posts/" + postId + "/delete");

        strategy.handle(request, response);

        verify(repository).deletePost(postId);
        verify(response).sendRedirect("/posts");
    }
}