package com.steps.postsapi.helpers;

import com.steps.postsapi.persistence.Post;
import com.steps.postsapi.persistence.User;

public class CreateForm {

    private Post post;
    private User user;

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
