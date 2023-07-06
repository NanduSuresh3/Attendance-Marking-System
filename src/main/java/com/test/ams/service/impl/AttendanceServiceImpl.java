package com.test.ams.service.impl;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.ams.constant.Messages;
import com.test.ams.dto.AttendanceDto;
import com.test.ams.dto.ResponseDto;
import com.test.ams.model.Attendance;
import com.test.ams.model.Student;
import com.test.ams.model.User;
import com.test.ams.repository.IAttendanceDao;
import com.test.ams.repository.IuserDao;
import com.test.ams.service.IAttendanceService;

@Service
public class AttendanceServiceImpl implements IAttendanceService {

	@Autowired
	private IAttendanceDao attendanceDao;

	@Autowired
	private IuserDao userDao;

	@Override
	public ResponseDto submitAttendance(AttendanceDto attendanceRequestDto, long userId) {
		try {

			Optional<User> userObj = userDao.findById(userId);
			if (!userObj.isEmpty()) {
				if (userObj.get().getType().equalsIgnoreCase("teacher")) {
					LocalDate currentDate = LocalDate.now();

					Optional<Attendance> attendanceExist = attendanceDao.findByDateAndStudentId(currentDate,
							attendanceRequestDto.getStudentId());
					if (attendanceExist.isEmpty()) {
						Attendance attendanceObj = new Attendance();
						BeanUtils.copyProperties(attendanceRequestDto, attendanceObj);
						Student studentObj = new Student();
						studentObj.setId(attendanceRequestDto.getStudentId());
						attendanceObj.setStudent(studentObj);
						attendanceDao.save(attendanceObj);

						return new ResponseDto(201, true, Messages.SUBMIT_ATTENDANCE, true);
					} else {
						AttendanceDto response = new AttendanceDto();
						response.setDate(attendanceExist.get().getDate());
						response.setId(attendanceExist.get().getId());
						response.setStudentId(attendanceExist.get().getStudent().getId());
						response.setStudentName(attendanceExist.get().getStudent().getName());
						if (attendanceExist.get().getAttendance().equals('Y')) {
							response.setAttendanceStatus("Present");
						} else {
							response.setAttendanceStatus("Absent");

						}
						return new ResponseDto(208, true, Messages.ATTENDANCE_EXIST, response);

					}

				} else {
					return new ResponseDto(400, false, Messages.STUDENT_RESTRICT_ATTENDANCE, false);

				}

			} else {
				return new ResponseDto(404, false, Messages.USER_NOT_FOUND, false);

			}
		} catch (Exception e) {
			return new ResponseDto(500, false, e.toString(), false);

		}

	}

}
