package com.imcs.test;

import java.util.List;

import com.imcs.dto.EmployeeDTO;
import com.imcs.services.EmployeesInfo;

public class Tester {

	public static void main(String[] args) throws Exception {
		EmployeesInfo e = new EmployeesInfo();
		List<EmployeeDTO> em = e.getAllEmployeeInfo();
		
		for (EmployeeDTO employeeDTO : em) {
			System.out.println(employeeDTO.getEmpId()+ " " + employeeDTO.getFirstName() + " " + employeeDTO.getLastName());
			System.out.println("********************");
		}	
	}

}
