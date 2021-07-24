package com.steps.postsapi.services.application;

import com.steps.postsapi.errorhandling.exceptions.UserNotExistException;
import com.steps.postsapi.persistence.Post;
import com.steps.postsapi.services.domain.PostService;
import com.steps.postsapi.services.domain.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class GetPostsApplicationService {

    @Autowired
    private ValidationService validationService;
    @Autowired
    private PostService postService;

    public List<Post> get(Long userId, Integer offset, Integer limit) throws UserNotExistException {
        if (userId != null){
            validationService.validateUserExists(userId);
        }

        List<Post> posts = postService.getPosts(userId, offset, limit);
        return posts;
    }
}
