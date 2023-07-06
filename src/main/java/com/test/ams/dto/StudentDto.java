package com.test.ams.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentDto {

	private long id;

	@NotBlank(message = "Name is mandatory")
	private String name;
	
	@NotNull(message= "Age is required")
	@Min(value=5, message=" min age 5 is required")
	private int age;

	@NotBlank(message = "Class Name is mandatory")
	private String className;

	private LocalDateTime registeredDate;

}
