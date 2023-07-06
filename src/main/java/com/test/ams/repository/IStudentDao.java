package com.test.ams.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.ams.model.Student;

@Repository
public interface IStudentDao extends JpaRepository<Student, Long> {

}
