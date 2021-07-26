package com.steps.postsapi.services.application;

import com.steps.postsapi.errorhandling.exceptions.MissingRequiredParameterException;
import com.steps.postsapi.errorhandling.exceptions.UserDetailsConflictException;
import com.steps.postsapi.helpers.IdsService;
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
    @Autowired
    private IdsService idsService;

    public Post createPostApplicationService(User user, Post post) throws UserDetailsConflictException, MissingRequiredParameterException {

        //TODO - Check why code is keep running (and creating the post) after throwing exception???
        validationService.inputValidation(post, user);
        idsService.generateUniqueId(post);
        userService.createOrUpdateUser(user, post.getId());
        Post newPost = postService.create(user.getId(), post);

        return newPost;
    }
}
