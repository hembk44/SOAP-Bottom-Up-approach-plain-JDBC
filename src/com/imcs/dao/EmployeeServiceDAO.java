package com.imcs.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.imcs.config.ApplicationConfig;
import com.imcs.constants.AppConstants;
import com.imcs.dto.EmployeeDTO;

public class EmployeeServiceDAO {
	
	public EmployeeServiceDAO() {
		
	}

	// Establish a connection to oracle db using jdbc
	public Connection getConnection() throws Exception {
		Connection con = null;
		ApplicationConfig config = ApplicationConfig.getInstance();
		try {
			Class.forName(config.getProperty(AppConstants.ORACLE_DRIVER));
			con = DriverManager.getConnection(config.getProperty(AppConstants.DB_URL),
					config.getProperty(AppConstants.DB_USERNAME), config.getProperty(AppConstants.DB_PASSWORD));

		} catch (ClassNotFoundException classNotFoundException) {
			classNotFoundException.printStackTrace();
			throw classNotFoundException;
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
			throw sqlException;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}
		return con;
	}

	// Retrieves list of employees
	public List<EmployeeDTO> getAllEmployeeInfo() {
		System.out.println("EmployeeServiceDAO.getAllEmployeeInfo -- BEGIN");
		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		EmployeeDTO empDto = null;
		List<EmployeeDTO> empList = null;

		try {
			empList = new ArrayList<EmployeeDTO>();
			con = getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT * FROM EMPLOYEES_TEST ORDER BY EMPLOYEE_ID");
			System.out.println("Employee Data: ");

			while (rs.next()) {
				System.out.println("Employee Data: " + rs.getString("EMPLOYEE_ID"));
				String employeeID = rs.getString("EMPLOYEE_ID");
				String firstName = rs.getString("FIRST_NAME");
				String lastName = rs.getString("LAST_NAME");
				String email = rs.getString("EMAIL");

				empDto = new EmployeeDTO(employeeID, firstName, lastName, email);
				empList.add(empDto);
				System.out.println("The employee is " + employeeID + " " + firstName + " " + lastName + " " + email);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
				stmt.close();
				rs.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}

		}
		System.out.println("EmployeeServiceDAO.getAllEmployeeInfo -- END");
		return empList;
	}

	// Retrieves an employee information based on their employee ID
	public EmployeeDTO getEmployeeInfo(String empId) {
		System.out.println("EmployeeServiceDAO.getEmployeeInfo -- BEGIN");
		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		EmployeeDTO empDto = null;

		try {
			con = getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT * FROM EMPLOYEES_TEST WHERE EMPLOYEE_ID = " + empId);
			System.out.println("Employee Data: ");

			while (rs.next()) {
				empDto = new EmployeeDTO();
				System.out.println("Employee Data: " + rs.getString("EMPLOYEE_ID"));
				String employeeID = rs.getString("EMPLOYEE_ID");
				String firstName = rs.getString("FIRST_NAME");
				String lastName = rs.getString("LAST_NAME");
				String email = rs.getString("EMAIL");

				empDto.setEmpId(employeeID);
				empDto.setFirstName(firstName);
				empDto.setLastName(lastName);
				empDto.setEmailId(email);

				System.out.println("The employee is " + employeeID + " " + firstName + " " + lastName + " " + email);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
				stmt.close();
				rs.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}

		}
		System.out.println("EmployeeServiceDAO.getEmployeeInfo -- END");
		return empDto;
	}

	// Adds an employee record or details(employee ID, firstname, lastname, and
	// email) to EMPLOYEES_TEST table
	public boolean addEmployeeInfo(int empID, String empFirstName, String empLastName, String empEmail)
			throws Exception {
		PreparedStatement pStmt;
		boolean isEmpAdded = false;
		String insertQuery = "insert into employees_test(employee_id, first_name, last_name, email)"
				+ "values(?,?,?,?)";
		try {
			Connection con = getConnection();
			pStmt = con.prepareStatement(insertQuery);
			pStmt.setInt(1, empID);
			pStmt.setString(2, empFirstName);
			pStmt.setString(3, empLastName);
			pStmt.setString(4, empEmail);
			int insertCheck = pStmt.executeUpdate();
			if (insertCheck == 1) {
				isEmpAdded = true;
				pStmt.close();
			} else if (insertCheck == 0) {
				isEmpAdded = false;
				pStmt.close();
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return isEmpAdded;

	}

	// update the specific row or record in the employees table
	public boolean updateEmployeeInfoUsingPreparedStmt(String pEmpId, String pFirstName, String pLastName,
			String pEmail) throws Exception {
		PreparedStatement pStmt = null;
		Connection con = null;
		boolean isEmpUpdated = false;
		String query = "UPDATE employees_test SET first_name = ? , " + "last_name = ? , " + "EMAIL = ? "
				+ "WHERE employee_id = ?";
		try {
			con = getConnection();
			pStmt = con.prepareStatement(query);
			pStmt.setString(1, pFirstName);
			pStmt.setString(2, pLastName);
			pStmt.setString(3, pEmail);
			pStmt.setString(4, pEmpId);
			int updateRow = pStmt.executeUpdate();
			if (updateRow == 1) {
				isEmpUpdated = true;
			} else if (updateRow == 0) {
				isEmpUpdated = false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				pStmt.close();
				con.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}

		}

		return isEmpUpdated;
	}

	// Removes an existing record in the table
	public boolean removeEmployeeInfo(String pEmpId) throws Exception {
		PreparedStatement pStmt = null;
		Connection con = null;
		String deleteMsg = null;
		boolean isEmpDeleted = false;
		String query = "DELETE FROM employees_test WHERE employee_id = ?";
		try {
			con = getConnection();
			pStmt = con.prepareStatement(query);
			pStmt.setString(1, pEmpId);
			int deletedRow = pStmt.executeUpdate();
			if (deletedRow == 1) {
				isEmpDeleted = true;
				deleteMsg = "Employee with ID " + pEmpId + " is successfully deleted!";
				System.out.println("Employee with ID " + pEmpId + " is successfully deleted!");
			} else if (deletedRow == 0) {
				isEmpDeleted = false;
				System.out.println("Delete Unsucessful!!!");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				pStmt.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}

		}
		return isEmpDeleted;

	}
}
