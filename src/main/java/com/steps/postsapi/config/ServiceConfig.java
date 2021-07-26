package com.steps.postsapi.config;

import com.steps.postsapi.helpers.IdsService;
import com.steps.postsapi.services.application.CalculateRuntimesApplicationService;
import com.steps.postsapi.services.application.CreatePostApplicationService;
import com.steps.postsapi.services.application.GetPostNumberApplicationService;
import com.steps.postsapi.services.application.GetPostsApplicationService;
import com.steps.postsapi.services.application.GetTopCreatorsApplicationService;
import com.steps.postsapi.services.domain.PostService;
import com.steps.postsapi.services.domain.RuntimeCalculationService;
import com.steps.postsapi.services.domain.UserService;
import com.steps.postsapi.services.domain.ValidationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
    public CreatePostApplicationService createPostApplicationService(){
        return new CreatePostApplicationService();
    }

    @Bean
    public GetPostsApplicationService getPostsApplicationService(){
        return new GetPostsApplicationService();
    }

    @Bean
    public GetPostNumberApplicationService getPostNumberApplicationService(){
        return new GetPostNumberApplicationService();
    }

    @Bean
    public GetTopCreatorsApplicationService getTopCreatorsApplicationService(){
        return new GetTopCreatorsApplicationService();
    }

    @Bean
    public CalculateRuntimesApplicationService calculateRuntimesApplicationService(){
        return new CalculateRuntimesApplicationService();
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

    @Bean
    public IdsService idsService(){
        return new IdsService();
    }

    @Bean
    public RuntimeCalculationService runtimeCalculationService(){
        return new RuntimeCalculationService();
    }
}
