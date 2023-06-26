package com.masai.model;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer employeeId;

	@Size(min = 3, max = 10, message = "Employee name should be min 3 and max 10")
	private String employeeName;
	
	
	@Pattern(regexp="(^$|[0-9]{10})")
	private String phoneNumber;
	
	@Email(message = "Email should be in a proper formate")
	private String email;

	private String address;
	
	@Enumerated
	private Education education;
	
	private Integer totalYearOfExperience;
	

	
	
	
}
