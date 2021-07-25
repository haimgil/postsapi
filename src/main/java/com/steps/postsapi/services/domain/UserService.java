package com.steps.postsapi.services.domain;

import com.steps.postsapi.errorhandling.exceptions.UserDetailsConflictException;
import com.steps.postsapi.helpers.IdsHelper;
import com.steps.postsapi.persistence.User;
import com.steps.postsapi.persistence.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

public class UserService {

    private final static Logger logger = LoggerFactory.getLogger(UserService.class);

    private final String COMMA = ",";

    @Autowired
    private UserRepository repository;
    @Autowired
    private ValidationService validationService;

    public void createOrUpdateUser(User user, Long postId) throws UserDetailsConflictException {
        if (repository.existsById(user.getId())){
            logger.info("Updating existed user");
            User userToUpdate = repository.findById(user.getId()).get();
            validationService.validateUserDetails(user, userToUpdate);
            userToUpdate.setPostIds(userToUpdate.getPostIds().concat(COMMA + postId));
            userToUpdate.setPostsQuantity(userToUpdate.getPostsQuantity() + 1);
            repository.save(userToUpdate);
            logger.info("User with id <" + userToUpdate.getId() + "> was updated");
        }
        else{
            createNewUser(user, postId);
        }
    }

    public boolean userExists(Long userId){
        return repository.existsById(userId);
    }

    private void createNewUser(User user, Long postId) {
        User newUser = new User();
        newUser.setId(user.getId());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setPostIds(String.valueOf(postId));
        newUser.setPostsQuantity(1);

        repository.save(newUser);
        logger.info("New user with id <" + newUser.getId() + "> was created");
    }

    public List<Long> getUserPostIds(Long userId) {
        return IdsHelper.commaSeparatedToList(repository
                .findById(userId)
                .get()
                .getPostIds());
    }

    public List<User> getTopCreators() {
        if (repository.count() >= 10) {
            return repository
                    .findAll(PageRequest.of(0, 10, Sort.by("postsQuantity").descending()))
                    .toList();
        }
        else{
            return repository.findAll(Sort.by("postsQuantity").descending());
        }
    }
}
