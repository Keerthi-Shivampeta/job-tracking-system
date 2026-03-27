package com.jobtracker.jobtracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobtracker.jobtracker.model.User;

public interface UserRepo extends JpaRepository<User, Long> {

	User findByEmail(String email);

}
