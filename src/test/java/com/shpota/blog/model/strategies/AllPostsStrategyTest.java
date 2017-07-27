package com.shpota.blog.model.strategies;

import com.shpota.blog.model.BlogRepository;
import com.shpota.blog.model.Post;
import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.OffsetDateTime;
import java.util.List;

import static com.shpota.blog.model.strategies.Strategy.DATE_FORMATTER;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class AllPostsStrategyTest {
    @Test
    public void shouldHandle() throws Exception {
        BlogRepository repository = mock(BlogRepository.class);
        Strategy strategy = new AllPostsStrategy(repository);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        String postedText = "In London, a company wants to turn street lamps into charging points for " +
                "electric cars. This is part of a pilot project. The company puts devices " +
                "into the lamp poles. An electric car driver can then just find a street lamp.";
        List<Post> posts = asList(
                new Post(1, "Title_1", OffsetDateTime.now(), "Text_1"),
                new Post(2, "Title_2", OffsetDateTime.now(), postedText)
        );
        given(repository.getAllPost()).willReturn(posts);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        given(request.getRequestDispatcher("/posts.jsp")).willReturn(dispatcher);

        strategy.handle(request, response);

        verify(request).setAttribute("posts", posts);
        verify(request).setAttribute("formatter", DATE_FORMATTER);
        verify(dispatcher).forward(request, response);
        assertEquals(postedText.substring(0, 100) + "...", posts.get(1).getPostedText());
    }
}