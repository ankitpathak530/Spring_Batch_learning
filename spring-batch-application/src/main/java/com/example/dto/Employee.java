package com.example.dto;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Employee {

	@Id
	private Integer employeeId;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String salary;
	
}
