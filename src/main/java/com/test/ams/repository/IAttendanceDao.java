package com.test.ams.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.test.ams.model.Attendance;

@Repository
public interface IAttendanceDao extends JpaRepository<Attendance, Long> {

	@Query("select a from Attendance a where date=:currentDate and a.student.id =:studentId")
	public Optional<Attendance> findByDateAndStudentId(LocalDate currentDate, long studentId);

}
