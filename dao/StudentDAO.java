package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import database.DBConnection;
import model.Student;

public class StudentDAO {

    // ================= Student Registration =================

    public boolean registerStudent(Student student) {

        try {

            Connection con = DBConnection.getConnection();

            String sql = "INSERT INTO student(name,email,phone,password,status) VALUES(?,?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, student.getName());
            ps.setString(2, student.getEmail());
            ps.setString(3, student.getPhone());
            ps.setString(4, student.getPassword());
            ps.setString(5, "Pending");

            int rows = ps.executeUpdate();

            ps.close();

            return rows > 0;

        } catch (Exception e) {

            e.printStackTrace();

        }

        return false;
    }

    // ================= Student Login =================

    public boolean loginStudent(String email, String password) {

        try {

            Connection con = DBConnection.getConnection();

            String sql = "SELECT * FROM student WHERE email=? AND password=? AND status='Approved'";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return true;

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return false;
    }

    // ================= Get Student ID =================

    public int getStudentId(String email) {

        int id = -1;

        try {

            Connection con = DBConnection.getConnection();

            String sql = "SELECT student_id FROM student WHERE email=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                id = rs.getInt("student_id");

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return id;
    }

    // ================= Get Student Name =================

    public String getStudentName(String email) {

        String name = "";

        try {

            Connection con = DBConnection.getConnection();

            String sql = "SELECT name FROM student WHERE email=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                name = rs.getString("name");

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return name;
    }

    // ================= Pending Students =================

    public ResultSet getPendingStudents() {

        try {

            Connection con = DBConnection.getConnection();

            String sql = "SELECT * FROM student WHERE status='Pending'";

            PreparedStatement ps = con.prepareStatement(sql);

            return ps.executeQuery();

        } catch (Exception e) {

            e.printStackTrace();

        }

        return null;
    }

    // ================= Approve Student =================

    public void approveStudent(int id) {

        try {

            Connection con = DBConnection.getConnection();

            String sql = "UPDATE student SET status='Approved' WHERE student_id=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, id);

            ps.executeUpdate();

            ps.close();

        } catch (Exception e) {

            e.printStackTrace();

        }
    }
    public boolean changePassword(int studentId, String oldPassword, String newPassword) {

        try {

            Connection con = DBConnection.getConnection();

            String checkSql = "SELECT * FROM student WHERE student_id=? AND password=?";

            PreparedStatement check = con.prepareStatement(checkSql);

            check.setInt(1, studentId);
            check.setString(2, oldPassword);

            ResultSet rs = check.executeQuery();

            if(rs.next()){

                String updateSql = "UPDATE student SET password=? WHERE student_id=?";

                PreparedStatement update = con.prepareStatement(updateSql);

                update.setString(1, newPassword);
                update.setInt(2, studentId);

                return update.executeUpdate() > 0;

            }

        } catch(Exception e){

            e.printStackTrace();

        }

        return false;
    }
}