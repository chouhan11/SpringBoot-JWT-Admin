package com.inno.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inno.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	 User findByUserName(String name);

}
