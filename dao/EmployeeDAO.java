package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import database.DBConnection;
import model.Employee;

public class EmployeeDAO {

	// Employee Registration
	public boolean registerEmployee(Employee employee) {

		try {

			Connection con = DBConnection.getConnection();

			String sql = "INSERT INTO employee(name,email,phone,password,status) VALUES(?,?,?,?,?)";

			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, employee.getName());
			ps.setString(2, employee.getEmail());
			ps.setString(3, employee.getPhone());
			ps.setString(4, employee.getPassword());
			ps.setString(5, "Pending");

			int rows = ps.executeUpdate();

			return rows > 0;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	// Get Pending Employees
	public ResultSet getPendingEmployees() {

		try {

			Connection con = DBConnection.getConnection();

			PreparedStatement ps = con.prepareStatement(
					"SELECT * FROM employee WHERE status='Pending'");

			return ps.executeQuery();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	// Approve Employee
	public void approveEmployee(int id) {

		try {

			Connection con = DBConnection.getConnection();

			PreparedStatement ps = con.prepareStatement(
					"UPDATE employee SET status='Approved' WHERE employee_id=?");

			ps.setInt(1, id);

			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	// Employee Login
	public boolean loginEmployee(String email, String password) {

		try {

			Connection con = DBConnection.getConnection();

			String sql = "SELECT * FROM employee WHERE email=? AND password=? AND status='Approved'";

			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, email);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();

			if(rs.next()) {
				return true;
			}

		} catch(Exception e) {
			e.printStackTrace();
		}

		return false;
	}
	public int getEmployeeId(String email){

		int id = -1;

		try{

			Connection con = DBConnection.getConnection();

			PreparedStatement ps = con.prepareStatement(
					"SELECT employee_id FROM employee WHERE email=?");

			ps.setString(1,email);

			ResultSet rs = ps.executeQuery();

			if(rs.next()){

				id = rs.getInt("employee_id");

			}

		}catch(Exception e){

			e.printStackTrace();

		}

		return id;

	}
	public boolean changePassword(int employeeId,
			String oldPassword,
			String newPassword){

		try{

			Connection con = DBConnection.getConnection();

			String check =
					"SELECT * FROM employee WHERE employee_id=? AND password=?";

			PreparedStatement ps = con.prepareStatement(check);

			ps.setInt(1,employeeId);
			ps.setString(2,oldPassword);

			ResultSet rs = ps.executeQuery();

			if(rs.next()){

				PreparedStatement update = con.prepareStatement(
						"UPDATE employee SET password=? WHERE employee_id=?");

				update.setString(1,newPassword);
				update.setInt(2,employeeId);

				return update.executeUpdate()>0;

			}

		}

		catch(Exception e){

			e.printStackTrace();

		}

		return false;

	}

}