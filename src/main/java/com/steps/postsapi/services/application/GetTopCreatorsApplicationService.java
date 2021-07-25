package com.steps.postsapi.services.application;

import com.steps.postsapi.persistence.User;
import com.steps.postsapi.services.domain.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class GetTopCreatorsApplicationService {

    @Autowired
    private UserService userService;

    public List<User> getTopCreators(){
        return userService.getTopCreators();
    }
}
