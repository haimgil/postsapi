package com.steps.postsapi.services.domain;

import com.steps.postsapi.persistence.Post;
import com.steps.postsapi.persistence.User;
import com.steps.postsapi.services.application.CreatePostApplicationService;
import com.steps.postsapi.services.application.GetPostsApplicationService;
import org.springframework.beans.factory.annotation.Autowired;

public class RuntimeCalculationService {

    private final static Long RUNTIME_ID = Integer.toUnsignedLong(1010101010);

    @Autowired
    private CreatePostApplicationService createPostApplicationService;
    @Autowired
    private GetPostsApplicationService getPostsApplicationService;

    public Long calculateAverageRuntime() {
        Long createPostRuntimeInMillis = calculateCreateRuntime();
        Long getPostRuntimeInMillis = calculateGetRuntime();

        return (createPostRuntimeInMillis + getPostRuntimeInMillis)/2;
    }

    private Long calculateCreateRuntime() {
        long startTime = System.nanoTime();
        createPostApplicationService.createPostApplicationService(generateRuntimeUser(), generateRuntimePost());
        long endTime = System.nanoTime();

        return (endTime - startTime)/1000000; //Divide by 1000000 to get millis
    }

    private Post generateRuntimePost() {
        Post post = new Post();
        post.setTitle("runtime");
        post.setBody("runtime");

        return post;
    }

    private User generateRuntimeUser() {
        User user = new User();
        user.setId(RUNTIME_ID);
        user.setFirstName("runtime");
        user.setLastName("runtime");

        return user;
    }

    private Long calculateGetRuntime() {
        long startTime = System.nanoTime();
        getPostsApplicationService.get(null,null,null);
        long endTime = System.nanoTime();

        return (endTime - startTime)/1000000; //Divide by 1000000 to get millis
    }
}
