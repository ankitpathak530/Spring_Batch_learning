package com.example.dto;

import lombok.Data;



@Data
public class EmployeeDto {

	private Integer employeeId;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String salary;
}
