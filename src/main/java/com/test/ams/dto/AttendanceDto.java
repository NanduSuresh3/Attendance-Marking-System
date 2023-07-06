package com.test.ams.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AttendanceDto {

	private long id;

	private LocalDate date;

	private long studentId;

	private Character attendance;

	private String StudentName;

	private String attendanceStatus;

}
