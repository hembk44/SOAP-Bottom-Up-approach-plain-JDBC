package com.imcs.bo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.imcs.dao.EmployeeServiceDAO;
import com.imcs.dto.EmployeeDTO;

public class EmployeeServiceBO {
	

	public EmployeeServiceBO() {
	
	}

	// Retrieves list of employees
	public List<EmployeeDTO> getAllEmployeeInfo() throws Exception {
		System.out.println("EmployeeServiceBO.getAllEmployeeInfo -- BEGIN");
		List<EmployeeDTO> empList = null;
		EmployeeServiceDAO empDao = null;

		try {
			empDao = new EmployeeServiceDAO();
			empList = empDao.getAllEmployeeInfo();
			if (empList == null) {
				throw new Exception("No employee rescords in the database. Please insert employee record!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		System.out.println("EmployeeServiceBO.getAllEmployeeInfo -- END");
		return empList;
	}

	public EmployeeDTO getEmployeeInfo(String empId) throws Exception {
		System.out.println("EmployeeServiceBO.getEmployeeInfo -- BEGIN");
		EmployeeServiceDAO dao = null;
		EmployeeDTO empDto = null;
		boolean isNumber = false;
		try {
			isNumber = Pattern.matches("\\d+", empId);

			if (empId != null && isNumber) {
				dao = new EmployeeServiceDAO();
				empDto = dao.getEmployeeInfo(empId);
				if (empDto == null) {
					throw new Exception("Employee with ID " + empId + " doesn't exists");
				}
			} else {
				throw new Exception("Invalid Input... Please provide valid inputs(Integer only)");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		System.out.println("EmployeeServiceBO.getEmployeeInfo -- END");
		return empDto;
	}

	// Adds an employee record or details(employee ID, firstname, lastname, and
	// email) to EMPLOYEES_TEST table
	public String addEmployeeInfo(int empID, String empFirstName, String empLastName, String empEmail)
			throws Exception {
		boolean isEmpAdded = false;
		EmployeeServiceDAO dao = null;
		EmployeeDTO empDto = null;
		String empAddMsg = null;
		try {
			dao = new EmployeeServiceDAO();
			isEmpAdded = dao.addEmployeeInfo(empID, empFirstName, empLastName, empEmail);
			if (isEmpAdded) {
				isEmpAdded = true;
				System.out.println("Employee with ID " + empID + " is added!");
				empDto = getEmployeeInfo(String.valueOf(empID));
				empAddMsg = "The employee: " + empDto.getEmpId() + " " + empDto.getFirstName() + " "
						+ empDto.getLastName() + " " + empDto.getEmailId() + " is successfully added!";

			} else {
				System.out.println("Insertion unsuccessful!");
				throw new Exception("Insertion of Employee with ID " + empID + "is unsuccessful!");
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return empAddMsg;

	}

	// update the specific row or record in the employees table
	public String updateEmployeeInfoUsingPreparedStmt(String pEmpId, String pFirstName, String pLastName, String pEmail)
			throws Exception {
		String updateMsg = null;
		boolean isEmpUpdated = false;
		EmployeeServiceDAO dao = null;
		try {
			dao = new EmployeeServiceDAO();
			isEmpUpdated = dao.updateEmployeeInfoUsingPreparedStmt(pEmpId, pFirstName, pLastName, pEmail);
			if (isEmpUpdated) {
				System.out.println("Employee with ID " + pEmpId + " is successfully updated!");
				updateMsg = "Employee with ID " + pEmpId + " is successfully updated!";
			} else {
				isEmpUpdated = false;
				System.out.println("update Unsucessful!!!");
				throw new Exception("Update of Employee with ID " + pEmpId + "is unsuccessful!");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		return updateMsg;
	}

	// Removes an existing record in the table
	public String removeEmployeeInfo(String pEmpId) throws Exception {
		String deleteMsg = null;
		boolean isEmpDeleted = false;
		try {
			EmployeeServiceDAO dao = new EmployeeServiceDAO();
			isEmpDeleted = dao.removeEmployeeInfo(pEmpId);
			if (isEmpDeleted) {
				deleteMsg = "Employee with ID " + pEmpId + " is successfully deleted!";
				System.out.println("Employee with ID " + pEmpId + " is successfully deleted!");
			} else {
				System.out.println("Delete Unsucessful!!!");
				throw new Exception("Deletion of Employee with ID " + pEmpId + "is unsuccessful!");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return deleteMsg;

	}
}
