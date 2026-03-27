package com.jobtracker.jobtracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobtracker.jobtracker.model.Job;
import com.jobtracker.jobtracker.model.User;

public interface JobRepo extends JpaRepository<Job, Long> {

	List<Job> findByUser(User user);
	

}
