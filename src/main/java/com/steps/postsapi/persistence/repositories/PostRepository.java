package com.steps.postsapi.persistence.repositories;

import com.steps.postsapi.persistence.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {
}
