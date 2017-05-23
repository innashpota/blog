package com.shpota.blog.model;

import java.time.OffsetDateTime;

public class Post {
    private int postId;
    private String title;
    private OffsetDateTime postedDate;
    private String postedText;

    public Post(int postId, String title, OffsetDateTime postedDate, String postedText) {
        this(title, postedDate, postedText);
        if (postId <= 0) {
            throw new IllegalArgumentException("ID must be positive.");
        }
        this.postId = postId;
    }

    public Post (String title, OffsetDateTime postedDate, String postedText) {
        if (title == null || postedDate == null || postedText == null) {
            throw new IllegalArgumentException("Title, date and text must not be null.");
        }
        this.title = title;
        this.postedDate = postedDate;
        this.postedText = postedText;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        if (postId <= 0) {
            throw new IllegalArgumentException("ID must be positive.");
        }
        this.postId = postId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null) {
            throw new IllegalArgumentException("Title must not be null.");
        }
        this.title = title;
    }

    public OffsetDateTime getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(OffsetDateTime postedDate) {
        if (title == null) {
            throw new IllegalArgumentException("Date must not be null.");
        }
        this.postedDate = postedDate;
    }

    public String getPostedText() {
        return postedText;
    }

    public void setPostedText(String postedText) {
        if (title == null) {
            throw new IllegalArgumentException("Text must not be null.");
        }
        this.postedText = postedText;
    }
}