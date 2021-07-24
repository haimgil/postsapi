package com.steps.postsapi.services;

import com.steps.postsapi.persistence.Post;
import com.steps.postsapi.persistence.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.UUID;

@Service
public class PostService {

    @Autowired
    private PostRepository repository;

    public Post create(Long userId, Post post){
        Post newPost = new Post();
        newPost.setId(generatePositiveUniqueId());
        newPost.setTitle(post.getTitle());
        newPost.setBody(post.getBody());
        newPost.setPublishDate(Calendar.getInstance().getTime());
        newPost.setUserId(userId);

        return repository.save(newPost);
    }

    private Long generatePositiveUniqueId() {
        long id = -1;
        do {
            id = UUID.randomUUID().getMostSignificantBits();
        } while (id < 0);
        return id;
    }
}
