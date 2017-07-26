package com.shpota.blog.model.strategies;

import com.shpota.blog.model.BlogRepository;
import com.shpota.blog.model.Post;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class AddPostStrategyTest {
    @Test
    public void shouldHandle() throws Exception {
        BlogRepository repository = mock(BlogRepository.class);
        Strategy strategy = new AddPostStrategy(repository);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        String title = "Title";
        String postedText = "Posted text";
        given(request.getParameter("title")).willReturn(title);
        given(request.getParameter("context")).willReturn(postedText);
        ArgumentCaptor<Post> captor = ArgumentCaptor.forClass(Post.class);
        int postId = 4;
        given(repository.addPost(any(Post.class))).willReturn(postId);

        strategy.handle(request, response);

        verify(repository).addPost(captor.capture());
        verify(response).sendRedirect("/posts/" + postId);
        assertEquals(title, captor.getValue().getTitle());
        assertEquals(postedText, captor.getValue().getPostedText());
    }
}