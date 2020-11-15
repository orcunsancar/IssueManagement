package com.orcunsancar.issuemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orcunsancar.issuemanagement.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUserName(String userName);
}
