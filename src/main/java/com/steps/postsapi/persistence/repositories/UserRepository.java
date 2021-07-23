package com.steps.postsapi.persistence.repositories;

import com.steps.postsapi.persistence.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
