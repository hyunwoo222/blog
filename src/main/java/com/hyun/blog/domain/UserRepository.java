package com.hyun.blog.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findFirstByEmailAndPassword(String email, String password);

    boolean existsByEmail(String email);
}
