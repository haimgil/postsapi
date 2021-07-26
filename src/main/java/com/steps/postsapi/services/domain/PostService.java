package com.steps.postsapi.services.domain;

import com.steps.postsapi.persistence.Post;
import com.steps.postsapi.persistence.repositories.PostRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class PostService {

    private final static Logger logger = LoggerFactory.getLogger(PostService.class);
    private final static long RUNTIME_ID = 1010101010;

    @Value("${pagination.offset}")
    private Integer defaultOffset;
    @Value("${pagination.limit}")
    private Integer defaultLimit;

    @Autowired
    private PostRepository repository;
    @Autowired
    private UserService userService;

    public Post create(Long userId, Post post){
        Post newPost = new Post(post.getTitle(), post.getBody());
        newPost.setId(post.getId());
        newPost.setBody(post.getBody());
        newPost.setPublishDate(Calendar.getInstance().getTime());
        newPost.setByUser(userId);

        if (userId == RUNTIME_ID){
            // In runtime check, do not want to persist new post in the db
            return newPost;
        }
        return repository.save(newPost);
    }

    public List<Post> getPosts(Long userId, Integer offset, Integer limit){
        Pageable pageable = paginateSortedByDate(offset, limit);
        if (userId != null){
            List<Long> userPostIds = userService.getUserPostIds(userId);
            List<Post> posts = repository.findAllById(userPostIds);
            posts.sort(Post::compareTo);
            Collections.sort(posts, Collections.reverseOrder());
            posts = paginatedUsrPosts(posts, pageable.getOffset(), pageable.getPageSize());
            return posts;
        }
        else {
            Page<Post> postsPage = null;
            postsPage = repository.findAll(pageable);
            return postsPage.toList();
        }

    }

    private List<Post> paginatedUsrPosts(List<Post> posts, long offset, int pageSize) {
        if (posts.size() < pageSize - offset)
            return posts;
        return posts.subList((int)offset,pageSize);
    }

    private Pageable paginateSortedByDate(Integer offset, Integer limit) {
        if (offset == null){
            offset = defaultOffset;
        }
        if (limit == null){
            limit = defaultLimit;
        }
        if (offset < 0){
            logger.info("Offset cannot be negative (" + offset + "). Set default value");
            offset = defaultOffset;
        }
        if (limit < 1){
            logger.info("Page size must be at least 1. Set default value");
            limit = defaultLimit;
        }
        return PageRequest.of(offset, limit, Sort.by("publishDate").descending());
    }

    public Long getNumber() {
        return repository.count();
    }
}
