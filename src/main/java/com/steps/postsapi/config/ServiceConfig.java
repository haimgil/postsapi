package com.steps.postsapi.config;

import com.steps.postsapi.services.CreatePostApplicationService;
import com.steps.postsapi.services.PostService;
import com.steps.postsapi.services.UserService;
import com.steps.postsapi.services.ValidationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
    public CreatePostApplicationService createPostApplicationService(){
        return new CreatePostApplicationService();
    }
    @Bean
    public ValidationService validationService(){
        return new ValidationService();
    }

    @Bean
    public UserService userService(){
        return new UserService();
    }

    @Bean
    public PostService newPostService(){
        return new PostService();
    }
}
