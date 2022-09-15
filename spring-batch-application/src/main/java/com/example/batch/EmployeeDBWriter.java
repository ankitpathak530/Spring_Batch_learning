package com.example.batch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.dto.Employee;
import com.example.repository.EmployeeRepository;


@Component
public class EmployeeDBWriter implements ItemWriter<Employee> {

	private EmployeeRepository employeeRepository;
	
	
	@Autowired
	public EmployeeDBWriter(EmployeeRepository employeeRepository){
		this.employeeRepository = employeeRepository;
	}
	
	
	
	@Override
	public void write(List<? extends Employee> items) throws Exception {
		this.employeeRepository.saveAll(items);
	}
}
