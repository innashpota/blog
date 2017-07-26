package com.shpota.blog.model.strategies;

import com.shpota.blog.model.BlogRepository;
import com.shpota.blog.model.Post;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.OffsetDateTime;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class AddPostStrategyTest {
    @Test
    public void handle() throws Exception {
        BlogRepository repository = mock(BlogRepository.class);
        AddPostStrategy strategy = new AddPostStrategy(repository);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        String title = "Title";
        String postedText = "Posted text";
        Post post = new Post(title, OffsetDateTime.now(), postedText);
        given(request.getParameter("title")).willReturn(title);
        given(request.getParameter("context")).willReturn(postedText);
        int postId = 0;
        given(repository.addPost(post)).willReturn(postId);

        strategy.handle(request, response);

        verify(response).sendRedirect("/posts/" + postId);
    }
}