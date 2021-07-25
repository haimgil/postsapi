package com.steps.postsapi.services.application;

import com.steps.postsapi.services.domain.PostService;
import org.springframework.beans.factory.annotation.Autowired;

public class GetPostNumberApplicationService {

    @Autowired
    private PostService postService;

    public Long getPostNumber(){
        return postService.getNumber();
    }
}
