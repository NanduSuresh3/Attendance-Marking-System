package com.test.ams.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.ams.dto.ResponseDto;
import com.test.ams.dto.StudentDto;
import com.test.ams.service.IStudentService;

@RestController
@RequestMapping("/student/")
public class StudentRestController {

	@Autowired
	private IStudentService studentService;

	/**
	 * to view all student details
	 * 
	 * @return
	 */
	@GetMapping("view")
	public ResponseDto getAllStudents() {
		return studentService.getAllStudents();
	}

	/**
	 * to add a new student
	 * 
	 * @param studentReqDto
	 * @return
	 */
	@PostMapping("register")
	public ResponseDto addStudent(@Valid @RequestBody StudentDto studentReqDto) {
		return studentService.studentRegister(studentReqDto);
	}

	/**
	 * to update a existing student
	 * 
	 * @param studentReqDto
	 * @return
	 */
	@PutMapping("update")
	public ResponseDto updateStudent(@Valid @RequestBody StudentDto studentReqDto) {
		return studentService.updateStudent(studentReqDto);
	}

	/**
	 * to delete a existing student using id
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping("delete")
	public ResponseDto deleteStudent(@RequestParam("id") long id) {
		return studentService.deleteStudent(id);
	}

}
