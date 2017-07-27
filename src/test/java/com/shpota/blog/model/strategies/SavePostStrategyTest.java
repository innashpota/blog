package com.shpota.blog.model.strategies;

import com.shpota.blog.model.BlogRepository;
import com.shpota.blog.model.Post;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.OffsetDateTime;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class SavePostStrategyTest {
    @Test
    public void shouldHandle() throws Exception {
        BlogRepository repository = mock(BlogRepository.class);
        Strategy strategy = new SavePostStrategy(repository);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        int postId = 4;
        given(request.getRequestURI()).willReturn("/posts/" + postId + "/edit");
        Post post = new Post("Title", OffsetDateTime.now(), "Posted text");
        String newTitle = "New title";
        String newPostedText = "New posted text";
        given(repository.getPost(postId)).willReturn(post);
        given(request.getParameter("title")).willReturn(newTitle);
        given(request.getParameter("context")).willReturn(newPostedText);

        strategy.handle(request, response);

        verify(repository).updatePost(postId, new Post(newTitle, post.getPostedDate(), newPostedText));
        verify(response).sendRedirect("/posts/" + postId);
    }
}