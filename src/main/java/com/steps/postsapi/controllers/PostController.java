package com.steps.postsapi.controllers;

import com.steps.postsapi.errorhandling.exceptions.MissingRequiredParameterException;
import com.steps.postsapi.errorhandling.exceptions.UserDetailsConflictException;
import com.steps.postsapi.helpers.CreateForm;
import com.steps.postsapi.persistence.Post;
import com.steps.postsapi.services.CreatePostApplicationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("steps/v0")
public class PostController {

    private final static Logger logger = LoggerFactory.getLogger(PostController.class);

    @Autowired
    private CreatePostApplicationService applicationService;

    @PostMapping("/posts")
    @ResponseBody
    public ResponseEntity<Post> createPost(@RequestBody CreateForm createForm) throws UserDetailsConflictException, MissingRequiredParameterException {
        Post post = applicationService.createPostApplicationService(createForm.getUser(), createForm.getPost());
        logger.info("Post id: {} was created successfully", post.getId());
        return ResponseEntity.ok(post);
    }

    @GetMapping("/posts")
    @ResponseBody
    public ResponseEntity<List<Post>> retrievePosts(@RequestParam(required = false)String userId, @RequestParam(required = false)Long offset, @RequestParam(required = false) Long limit){

        return ResponseEntity.ok(new ArrayList<>());
    }
}
