package com.example.mapper;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import com.example.dto.EmployeeDto;

public class EmployeeFieldRowMapper implements FieldSetMapper<EmployeeDto> {

	@Override
	public EmployeeDto mapFieldSet(FieldSet fieldSet) throws BindException {
	
		EmployeeDto employeeDto = new EmployeeDto();
		
		try {
			employeeDto.setEmployeeId(fieldSet.readInt("EMPLOYEE_ID"));
			employeeDto.setFirstName(fieldSet.readString("FIRST_NAME"));
			employeeDto.setLastName(fieldSet.readString("LAST_NAME"));
			employeeDto.setEmail(fieldSet.readString("EMAIL"));
			employeeDto.setPhone(fieldSet.readString("PHONE_NUMBER"));
			employeeDto.setSalary(fieldSet.readString("SALARY"));
		}
		catch(Exception e) {
			System.out.println("Exception occured while parsing data to Dto");
		}
		return employeeDto;
	}
}
