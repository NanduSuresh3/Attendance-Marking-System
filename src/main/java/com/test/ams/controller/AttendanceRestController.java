package com.test.ams.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.ams.dto.AttendanceDto;
import com.test.ams.dto.ResponseDto;
import com.test.ams.service.IAttendanceService;

@RestController
@RequestMapping("/attendance/")
public class AttendanceRestController {

	@Autowired
	private IAttendanceService attendanceService;

	/**
	 * API for submit attendance
	 * 
	 * @param userId
	 * @param attendanceRequestDto
	 * @return
	 */
	@PostMapping("submit")
	public ResponseDto submitAttendance(@RequestParam(value = "userId") long userId,
			@RequestBody AttendanceDto attendanceRequestDto) {

		return attendanceService.submitAttendance(attendanceRequestDto, userId);

	}

}
