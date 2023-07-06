package com.test.ams.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.ams.model.User;

@Repository
public interface IuserDao extends JpaRepository<User, Long> {

}
