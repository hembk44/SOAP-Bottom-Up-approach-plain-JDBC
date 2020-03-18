package com.imcs.dto;

import java.io.Serializable;

public class EmployeeDTO implements Serializable {
	private String empId;
	private String lastName;
	private String firstName;
	private String emailId;

	public EmployeeDTO() {

	}

	public EmployeeDTO(String empId, String lastName, String firstName, String emailId) {
		this.empId = empId;
		this.lastName = lastName;
		this.firstName = firstName;
		this.emailId = emailId;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
}
