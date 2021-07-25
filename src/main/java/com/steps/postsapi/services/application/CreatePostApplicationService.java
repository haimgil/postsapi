package com.steps.postsapi.services.application;

import com.steps.postsapi.errorhandling.exceptions.MissingRequiredParameterException;
import com.steps.postsapi.errorhandling.exceptions.UserDetailsConflictException;
import com.steps.postsapi.persistence.Post;
import com.steps.postsapi.persistence.User;
import com.steps.postsapi.services.domain.PostService;
import com.steps.postsapi.services.domain.UserService;
import com.steps.postsapi.services.domain.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;

public class CreatePostApplicationService {

    @Autowired
    private ValidationService validationService;
    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;

    public Post createPostApplicationService(User user, Post post) throws UserDetailsConflictException, MissingRequiredParameterException {

        //TODO - Check why code is keep running (and creating the post) after throwing exception???
        validationService.inputValidation(post, user);
        Post newPost = postService.create(user.getId(), post);
        userService.createOrUpdateUser(user, newPost.getId());

        return newPost;
    }
}
