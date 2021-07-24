package com.steps.postsapi.services;

import com.steps.postsapi.errorhandling.exceptions.MissingRequiredParameterException;
import com.steps.postsapi.errorhandling.exceptions.UserDetailsConflictException;
import com.steps.postsapi.persistence.Post;
import com.steps.postsapi.persistence.User;
import org.springframework.beans.factory.annotation.Autowired;

public class CreatePostApplicationService {

    @Autowired
    private ValidationService validationService;
    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;

    public Post createPostApplicationService(User user, Post post) throws UserDetailsConflictException, MissingRequiredParameterException {

        validationService.inputValidation(post, user);
        Post newPost = postService.create(user.getId(), post);
        userService.createOrUpdateUser(user, newPost.getId());

        return newPost;
    }
}
