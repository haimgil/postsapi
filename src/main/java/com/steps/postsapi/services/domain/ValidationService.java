package com.steps.postsapi.services.domain;

import com.steps.postsapi.errorhandling.errors.ErrorsMessages;
import com.steps.postsapi.errorhandling.exceptions.MissingRequiredParameterException;
import com.steps.postsapi.errorhandling.exceptions.UserDetailsConflictException;
import com.steps.postsapi.errorhandling.exceptions.UserNotExistException;
import com.steps.postsapi.persistence.Post;
import com.steps.postsapi.persistence.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidationService {

    private final static Logger logger = LoggerFactory.getLogger(ValidationService.class);

    @Autowired
    private UserService userService;

    public void inputValidation(Post post, User user) throws MissingRequiredParameterException{
        logger.info("Validate input");
        if (post.getTitle() == null){
            throwMissingParameterException("Post.Title");
        }else if (post.getBody() == null){
            throwMissingParameterException("Post.Body");
        }else if (user.getId() == null){
            throwMissingParameterException("User.Id");
        }else if (user.getFirstName() == null){
            throwMissingParameterException("User.FirstName");
        }else if (user.getLastName() == null){
            throwMissingParameterException("User.LastName");
        }
    }

    public void validateUserDetails(User existedUser, User userToUpdate) throws UserDetailsConflictException {
        logger.info("Validate user details");
        if ( ! existedUser.getFirstName().equalsIgnoreCase(userToUpdate.getFirstName())
        || ! existedUser.getLastName().equalsIgnoreCase(userToUpdate.getLastName())){

            String errMsg = String.format(ErrorsMessages.userDetailsConflictMsg, existedUser.getId());
            throw new UserDetailsConflictException(errMsg);
        }
    }

    public void validateUserExists(Long userId) throws UserNotExistException {
        logger.info("Validate user existence");
        if ( ! userService.userExists(userId)){
            String errMsg = String.format(ErrorsMessages.userNotExist, userId);
            throw new UserNotExistException(errMsg);
        }
    }

    private void throwMissingParameterException(String paramName) throws MissingRequiredParameterException {
        String errMsg = String.format(ErrorsMessages.missingRequiredParameterMsg, paramName);
        throw new MissingRequiredParameterException(errMsg);
    }
}
