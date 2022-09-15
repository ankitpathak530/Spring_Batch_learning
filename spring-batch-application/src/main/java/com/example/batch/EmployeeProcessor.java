package com.example.batch;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.example.dto.Employee;
import com.example.dto.EmployeeDto;


@Component
public class EmployeeProcessor implements ItemProcessor<EmployeeDto, Employee> {
      
	
	
	@Override
	public Employee process(EmployeeDto employeeDto) throws Exception
	{	
		Employee employee = new Employee();
		employee.setEmployeeId(employeeDto.getEmployeeId());
		employee.setFirstName(employeeDto.getFirstName());
		employee.setLastName(employeeDto.getLastName());
		employee.setEmail(employeeDto.getEmail());
		employee.setPhone(employeeDto.getPhone());
		employee.setSalary(employeeDto.getSalary());
		

		System.out.println("EmployeeProcessor processing file");
		return employee;
	}

	
	
	
	
}
