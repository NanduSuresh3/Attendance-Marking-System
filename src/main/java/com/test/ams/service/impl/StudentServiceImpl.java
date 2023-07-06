package com.test.ams.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.ams.constant.Messages;
import com.test.ams.dto.ResponseDto;
import com.test.ams.dto.StudentDto;
import com.test.ams.model.Student;
import com.test.ams.repository.IStudentDao;
import com.test.ams.service.IStudentService;

@Service
public class StudentServiceImpl implements IStudentService {

	@Autowired
	private IStudentDao studentDao;

	@Override
	public ResponseDto getAllStudents() {
		try {
			List<Student> students = studentDao.findAll();
			if (!students.isEmpty()) {

				List<StudentDto> allStudents = students.stream().map(student -> {
					StudentDto studentObj = new StudentDto();
					BeanUtils.copyProperties(student, studentObj);
					return studentObj;
				}).collect(Collectors.toList());
				return new ResponseDto(200, true, Messages.VIEW_STUDENTS, allStudents);

			} else {
				return new ResponseDto(404, false, Messages.STUDENTS_NOT_EXIST, false);
			}

		} catch (Exception e) {
			return new ResponseDto(500, false, e.toString(), false);
		}

	}

	@Override
	public ResponseDto studentRegister(StudentDto studentRequestDto) {
		try {

			Student studentObj = new Student();
			BeanUtils.copyProperties(studentRequestDto, studentObj);
			studentDao.save(studentObj);

			return new ResponseDto(201, true, Messages.ADD_STUDENT, true);

		} catch (Exception e) {
			return new ResponseDto(500, false, e.toString(), false);
		}

	}

	@Override
	public ResponseDto updateStudent(StudentDto studentRequestDto) {
		try {

			Optional<Student> student = studentDao.findById(studentRequestDto.getId());
			if (!student.isEmpty()) {

				studentRequestDto.setRegisteredDate(student.get().getRegisteredDate());

				BeanUtils.copyProperties(studentRequestDto, student.get());
				studentDao.save(student.get());

				return new ResponseDto(201, true, Messages.UPDATE_STUDENT, student);
			} else {
				return new ResponseDto(404, false, Messages.STUDENTS_NOT_EXIST, false);

			}

		} catch (Exception e) {
			return new ResponseDto(500, false, e.toString(), false);
		}
	}

	@Override
	public ResponseDto deleteStudent(long id) {
		try {

			Optional<Student> student = studentDao.findById(id);
			if (!student.isEmpty()) {
				studentDao.deleteById(id);

				return new ResponseDto(200, true, Messages.DELETE_STUDENT, true);
			} else {
				return new ResponseDto(404, false, Messages.STUDENTS_NOT_EXIST, false);
			}

		} catch (Exception e) {
			return new ResponseDto(500, false, e.toString(), false);
		}
	}

}
