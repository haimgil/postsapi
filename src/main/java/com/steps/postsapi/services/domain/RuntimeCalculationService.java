package com.steps.postsapi.services.domain;

import com.steps.postsapi.persistence.Post;
import com.steps.postsapi.persistence.User;
import com.steps.postsapi.services.application.CreatePostApplicationService;
import com.steps.postsapi.services.application.GetPostsApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class RuntimeCalculationService {

    private final static long RUNTIME_ID = 1010101010;
    private final String MILLIS = "millis";

    @Value("${time.unit}")
    private String timeUnit;

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

        if (MILLIS.equalsIgnoreCase(timeUnit))
            return (endTime - startTime)/1000000; //Divide by 1000000 to get millis
        else
            return (endTime - startTime); //nanos
    }

    private Post generateRuntimePost() {
        return new Post("runtime", "runtime");
    }

    private User generateRuntimeUser() {
        return new User(RUNTIME_ID,"runtime", "runtime");
    }

    private Long calculateGetRuntime() {
        long startTime = System.nanoTime();
        getPostsApplicationService.get(null,null,null);
        long endTime = System.nanoTime();

        return (endTime - startTime)/1000000; //Divide by 1000000 to get millis
    }
}
