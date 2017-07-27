package com.shpota.blog.model;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.time.OffsetDateTime;

import static org.junit.Assert.assertEquals;

public class PostTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void shouldFailToConstructGivenEmptyTitle() throws Exception {
        String title = null;
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Title must not be empty.");

        new Post(1, title, OffsetDateTime.now(), "Posted text");
    }

    @Test
    public void shouldFailToConstructGivenNullDate() throws Exception {
        OffsetDateTime dateTime = null;
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Date must not be null.");

        new Post(1, "title", dateTime, "Posted text");
    }

    @Test
    public void shouldFailToConstructGivenEmptyText() throws Exception {
        String postedText = null;
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Text must not be empty.");

        new Post(1, "title", OffsetDateTime.now(), postedText);
    }

    @Test
    public void shouldSetTitle() throws Exception {
        Post post = aPost();
        String title = "Hello world!";

        post.setTitle(title);

        assertEquals(title, post.getTitle());
    }

    @Test
    public void shouldFailToSetTitle() throws Exception {
        Post post = aPost();
        String title = null;
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Title must not be empty.");

        post.setTitle(title);
    }

    @Test
    public void shouldSetText() throws Exception {
        Post post = aPost();
        String postedText = "Hello world!";

        post.setPostedText(postedText);

        assertEquals(postedText, post.getPostedText());
    }

    @Test
    public void shouldFailToSetText() throws Exception {
        Post post = aPost();
        String postedText = null;
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Text must not be empty.");

        post.setPostedText(postedText);
    }

    private Post aPost() {
        return new Post(1, "Title", OffsetDateTime.now(), "Posted text");
    }
}