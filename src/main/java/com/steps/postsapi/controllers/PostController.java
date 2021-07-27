package com.steps.postsapi.controllers;

import com.steps.postsapi.errorhandling.exceptions.MissingRequiredParameterException;
import com.steps.postsapi.errorhandling.exceptions.UserDetailsConflictException;
import com.steps.postsapi.errorhandling.exceptions.UserNotExistException;
import com.steps.postsapi.jsonobjects.CreateForm;
import com.steps.postsapi.jsonobjects.PostNumber;
import com.steps.postsapi.persistence.Post;
import com.steps.postsapi.services.application.CreatePostApplicationService;
import com.steps.postsapi.services.application.GetPostNumberApplicationService;
import com.steps.postsapi.services.application.GetPostsApplicationService;
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

import java.util.List;

@RestController
@RequestMapping("steps/v0")
public class PostController {

    private static final Logger logger = LoggerFactory.getLogger(PostController.class);

    @Autowired
    private CreatePostApplicationService createPostApplicationService;
    @Autowired
    private GetPostsApplicationService getPostsApplicationService;
    @Autowired
    private GetPostNumberApplicationService postNumberApplicationService;

    @PostMapping("/posts")
    @ResponseBody
    public ResponseEntity<Post> createPost(@RequestBody CreateForm createForm) throws UserDetailsConflictException, MissingRequiredParameterException {
        Post post = createPostApplicationService.createPostApplicationService(createForm.getUser(), createForm.getPost());
        logger.info("Post id: {} was created successfully", post.getId());
        return ResponseEntity.ok(post);
    }

    @GetMapping("/posts")
    @ResponseBody
    public ResponseEntity<List<Post>> retrievePosts(@RequestParam(required = false)Long userId, @RequestParam(required = false)Integer offset, @RequestParam(required = false) Integer limit) throws UserNotExistException {

        List<Post> posts = getPostsApplicationService.get(userId, offset, limit);

        return ResponseEntity.ok(posts);
    }

    @GetMapping("/postnumber")
    @ResponseBody
    public ResponseEntity<PostNumber> postNumber(){
        Long postNumber = postNumberApplicationService.getPostNumber();
        return ResponseEntity.ok(new PostNumber(postNumber));
    }
}
