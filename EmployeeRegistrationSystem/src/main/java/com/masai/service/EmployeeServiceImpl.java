package com.masai.service;


import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.masai.exceptions.EmployeeException;
import com.masai.model.Employee;
import com.masai.repository.EmployeeDao;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeDao employeeDao;

	@Override
	public Employee registerEmployee(Employee employee) throws EmployeeException {

		Employee savedEmployee = employeeDao.save(employee);

		if (savedEmployee != null)
			return savedEmployee;
		else
			throw new EmployeeException("Employee Not Saved...");
	}
	

	@Override
	public Employee getEmployeeById(Integer employeeId) throws EmployeeException {


		return employeeDao.findById(employeeId)
				.orElseThrow(() -> new EmployeeException("Employee not found with id : " + employeeId));

	}

	

	@Override
	public Employee updateEmployee(Employee employee) throws EmployeeException {
		Optional<Employee> cOptional = employeeDao.findById(employee.getEmployeeId());

		if (cOptional.isPresent()) {
			return employeeDao.save(employee);
		} else {
			throw new EmployeeException("Employee not found with id : " + employee.getEmployeeId());
		}

	}


	@Override
	public List<Employee> getAllEmployeeDetails() throws EmployeeException {
		List<Employee> customers = employeeDao.findAll();

		if (customers.size() == 0)
			throw new EmployeeException("Employee not found..");
		else {
			return customers;
		}
	}


	@Override
	public Employee deleteEmployeeById(Integer employeeId) throws EmployeeException {
		

		Optional<Employee> employee = employeeDao.findById(employeeId);
		if (!employee.isEmpty()) {
			Employee existingEmployee = employee.get();

			employeeDao.delete(existingEmployee);
			return existingEmployee;
		} else
			throw new EmployeeException("Employee not found with id : " + employeeId);
	}





}
