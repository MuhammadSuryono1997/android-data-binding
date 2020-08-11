package com.yono.databindingtask.api.response;

import com.google.gson.annotations.SerializedName;

public class PostResponse {
    @SerializedName("title")
    private String titlePosts;

    @SerializedName("body")
    private String bodyPosts;

    public String getTitlePosts() {
        return titlePosts;
    }

    public void setTitlePosts(String titlePosts) {
        this.titlePosts = titlePosts;
    }

    public String getBodyPosts() {
        return bodyPosts;
    }

    public void setBodyPosts(String bodyPosts) {
        this.bodyPosts = bodyPosts;
    }
}
