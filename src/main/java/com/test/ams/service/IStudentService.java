package com.test.ams.service;

import com.test.ams.dto.ResponseDto;
import com.test.ams.dto.StudentDto;

public interface IStudentService {

	public ResponseDto getAllStudents();

	public ResponseDto studentRegister(StudentDto studentRequestDto);

	public ResponseDto updateStudent(StudentDto studentRequestDto);

	public ResponseDto deleteStudent(long id);

}
