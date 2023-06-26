package com.masai.service;


import java.util.List;
import com.masai.exceptions.EmployeeException;
import com.masai.model.Employee;


public interface EmployeeService {

	public Employee registerEmployee(Employee employee)throws EmployeeException;
	
	public Employee getEmployeeById(Integer employeeId)throws EmployeeException;
	
	public Employee updateEmployee(Employee employee)throws EmployeeException;
	
	public List<Employee> getAllEmployeeDetails()throws EmployeeException;
	
	public Employee deleteEmployeeById(Integer employeeId)throws EmployeeException;
}
