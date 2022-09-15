package com.example.config;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import com.example.dto.Employee;
import com.example.dto.EmployeeDto;
import com.example.mapper.EmployeeFieldRowMapper;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {
	

	

	
	//creating Job
	@Bean
	public Job demoJob(JobBuilderFactory jobBuilderFactory, 
			 		   StepBuilderFactory stepBuilderFactory,
			           ItemReader<EmployeeDto> EmployeeItemReader,
			           ItemProcessor<EmployeeDto, Employee> EmployeeItemProcessor,
			           ItemWriter<Employee> EmployeeItemWriter
			          ) throws Exception { 
	
		
		//creating Step
		Step step = stepBuilderFactory.get("step1")
										.<EmployeeDto,Employee>chunk(5)
										.reader(EmployeeItemReader)
										.processor(EmployeeItemProcessor)
										.writer(EmployeeItemWriter)
										.build();
								
		
		
		//creating Job
		return   jobBuilderFactory.get("demo1")
				.incrementer(new RunIdIncrementer())
				.start(step) // we can use next for multiple step to add
				.build();
	}
	
	
	
   
	

	// Reader
	@Bean
	@StepScope
	public FlatFileItemReader<EmployeeDto> employeeReader() throws Exception 
	{
		FlatFileItemReader<EmployeeDto> flatFileItemReader = new FlatFileItemReader<>();
		
	    flatFileItemReader.setResource(new FileSystemResource("src/main/resources/employees.csv"));
	    flatFileItemReader.setName("CSV-Reader");
		flatFileItemReader.setName("CSV-READER");
		flatFileItemReader.setLinesToSkip(1);
		flatFileItemReader.setLineMapper(lineMapper());
		
		System.out.println("SpringBatchConfig employeeReader...");
		
		return flatFileItemReader;
	}
	
	

	
	@Bean
	public LineMapper<EmployeeDto> lineMapper() 
	{
		  return new DefaultLineMapper<EmployeeDto>() {{		  
			         
				    setLineTokenizer(new DelimitedLineTokenizer() 
				    {{
				    		    
						setNames(new String[] {"EMPLOYEE_ID","FIRST_NAME","LAST_NAME","EMAIL","PHONE_NUMBER","SALARY"});
					    setDelimiter(","); 
					    setIncludedFields(0,1,2,3,4,7);
			            setStrict(false);
				    }});
				    
				    
				    setFieldSetMapper(new EmployeeFieldRowMapper());
				 		 	
		   }};
	}	
} 
