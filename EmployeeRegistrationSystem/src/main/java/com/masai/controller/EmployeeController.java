package com.masai.controller;


import java.util.List;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.masai.model.Employee;
import com.masai.service.EmployeeService;

@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping("/employee")
	public ResponseEntity<Employee> saveEmployeeController(@Valid @RequestBody Employee employee){
		       Employee savedEmployee= employeeService.registerEmployee(employee);
		       
		       return new ResponseEntity<Employee>(savedEmployee,HttpStatus.CREATED);
	}
	
	@GetMapping("/employee/{id}")
	public ResponseEntity<Employee> findEmployeeByIdController(@PathVariable("id") Integer employeeId){
		                   Employee employee=  employeeService.getEmployeeById(employeeId);
		                   
		                   return new ResponseEntity<Employee>(employee,HttpStatus.ACCEPTED);
	}
	
	

	
	@PatchMapping("/updateemployee")
	public ResponseEntity<Employee> updateEmployeeController(@Valid @RequestBody Employee employee ){
		                   Employee savedEmployee=  employeeService.updateEmployee(employee);
		                   
		                   return new ResponseEntity<Employee>(savedEmployee,HttpStatus.OK);
	}
	
	

	
	@GetMapping("/getallemployee")
	public ResponseEntity<List<Employee>> getAllEmployeeController(){
		List<Employee> employee=  employeeService.getAllEmployeeDetails();
		                   
		                   return new ResponseEntity<>(employee,HttpStatus.ACCEPTED);
	}
	
	
	@DeleteMapping("/employee/{id}")
	public ResponseEntity<Employee> detelEmployeeByIdController(@PathVariable("id") Integer id){
		Employee employee= employeeService.deleteEmployeeById(id);
		           
		           return new ResponseEntity<>(employee,HttpStatus.ACCEPTED);
	}
	

	
	
	
	
	
	
}
