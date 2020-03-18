package com.imcs.services;

import java.util.ArrayList;
import java.util.List;

import com.imcs.bo.EmployeeServiceBO;
import com.imcs.dao.EmployeeServiceDAO;
import com.imcs.dto.EmployeeDTO;
import com.imcs.exception.ApplicationException;
import com.imcs.pojo.ResponseDataArrList;

/**
 * Implementation of oracle database with java using jdbc Includes methods for
 * jdbc connection, SELECT, INSERT, UPDATE, and DELETE. This class also expose
 * SOAP web services for other applications.
 * 
 * @author Hem BK
 *
 */
public class EmployeesInfo {
	public EmployeesInfo() {
	
	}

	// Retrieves list of employees
	public List<EmployeeDTO> getAllEmployeeInfo() throws Exception {
		System.out.println("EmployeeService.getAllEmployeeInfo -- BEGIN");
		List<EmployeeDTO> empList = null;
		EmployeeServiceBO empBo = null;
		ResponseDataArrList employeeList = null;
		try {
			empBo = new EmployeeServiceBO();
			empList = empBo.getAllEmployeeInfo();
			employeeList = new ResponseDataArrList();
			employeeList.setEmpList(empList);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}
		System.out.println("EmployeeService.getAllEmployeeInfo -- END");
		return employeeList.getEmpList();
//		return empList;
	}

	// retrieve the list of employees from employees table
	public EmployeeDTO getEmployeeInfo(String empId) throws ApplicationException {
		System.out.println("EmployeesInfo.getEmployeeInfo -- BEGIN");
		EmployeeDTO empDto = null;
		try {
			EmployeeServiceBO empBo = new EmployeeServiceBO();
			empDto = empBo.getEmployeeInfo(empId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}
		System.out.println("EmployeesInfo.getEmployeeInfo -- END");
		return empDto;
	}

	public String addEmployeeInfo(int empID, String empFirstName, String empLastName, String empEmail)
			throws Exception {
		System.out.println("EmployeesInfo.addEmployeeInfo -- BEGIN");
		String empAddMsg = null;
		try {
			EmployeeServiceBO empBo = new EmployeeServiceBO();
			empAddMsg = empBo.addEmployeeInfo(empID, empFirstName, empLastName, empEmail);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}
		System.out.println("EmployeesInfo.addEmployeeInfo -- END");
		return empAddMsg;

	}

	// update the specific row or record in the employees table
	public String updateEmployeeInfo(String pEmpId, String pFirstName, String pLastName, String pEmail)
			throws Exception {
		System.out.println("EmployeesInfo.updateEmployeeInfoUsingPreparedStmt -- BEGIN");
		String updateMsg = null;

		try {
			EmployeeServiceBO empBo = new EmployeeServiceBO();
			updateMsg = empBo.updateEmployeeInfoUsingPreparedStmt(pEmpId, pFirstName, pLastName, pEmail);

		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}
		System.out.println("EmployeesInfo.updateEmployeeInfoUsingPreparedStmt -- END");
		return updateMsg;
	}

	// Removes an existing record in the table
	public String removeEmployeeInfo(String pEmpId) throws Exception {
		System.out.println("EmployeesInfo.removeEmployeeInfo -- BEGIN");
		String deleteMsg = null;
		try {
			EmployeeServiceBO empBo = new EmployeeServiceBO();
			deleteMsg = empBo.removeEmployeeInfo(pEmpId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}
		System.out.println("EmployeesInfo.removeEmployeeInfo -- END");
		return deleteMsg;
	}

}
