package com.shpota.blog.model;

import com.shpota.blog.util.Assert;

import java.time.OffsetDateTime;

public class Post {
    private int postId;
    private String title;
    private OffsetDateTime postedDate;
    private String postedText;

    public Post(int postId, String title, OffsetDateTime postedDate, String postedText) {
        this(title, postedDate, postedText);
        Assert.isPositive(postId, "ID must be positive.");
        this.postId = postId;
    }

    public Post(String title, OffsetDateTime postedDate, String postedText) {
        Assert.notEmpty(title, "Title must not be empty.");
        Assert.notNull(postedDate, "Date must not be null.");
        Assert.notEmpty(postedText, "Text must not be empty.");
        this.title = title;
        this.postedDate = postedDate;
        this.postedText = postedText;
    }

    public int getPostId() {
        return postId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        Assert.notEmpty(title, "Title must not be empty.");
        this.title = title;
    }

    public OffsetDateTime getPostedDate() {
        return postedDate;
    }

    public String getPostedText() {
        return postedText;
    }

    public void setPostedText(String postedText) {
        Assert.notEmpty(postedText, "Text must not be empty.");
        this.postedText = postedText;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        Post post = (Post) object;

        if (postId != post.postId) return false;
        if (!title.equals(post.title)) return false;
        if (!postedDate.equals(post.postedDate)) return false;
        return postedText.equals(post.postedText);
    }

    @Override
    public int hashCode() {
        int result = postId;
        result = 31 * result + title.hashCode();
        result = 31 * result + postedDate.hashCode();
        result = 31 * result + postedText.hashCode();
        return result;
    }
}