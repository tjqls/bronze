package com.example.bronze.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<SiteUser,Integer> {
    Optional<SiteUser> findByuserId(String userId);
}
