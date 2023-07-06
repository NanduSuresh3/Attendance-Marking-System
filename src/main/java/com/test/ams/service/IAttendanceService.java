package com.test.ams.service;

import com.test.ams.dto.AttendanceDto;
import com.test.ams.dto.ResponseDto;

public interface IAttendanceService {

	public ResponseDto submitAttendance(AttendanceDto attendanceRequestDto, long userId);

}
