package com.ap.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ap.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{
public User findByUserName(String userName);
}
