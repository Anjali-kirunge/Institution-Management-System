package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import database.DBConnection;
import model.Admission;

public class AdmissionDAO {

    public boolean registerCourse(Admission admission) {

        try {

            Connection con = DBConnection.getConnection();

            String sql = "INSERT INTO admission(student_id,course_id,admission_date,status) VALUES(?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, admission.getStudentId());
            ps.setInt(2, admission.getCourseId());
            ps.setDate(3, admission.getAdmissionDate());
            ps.setString(4, admission.getStatus());

            int rows = ps.executeUpdate();

            ps.close();

            return rows > 0;

        } catch (Exception e) {

            e.printStackTrace();

        }

        return false;
    }
    public ResultSet getMyCourses(int studentId){

        try{

            Connection con = DBConnection.getConnection();

            String sql =
            "SELECT c.course_name,c.duration,c.fees,a.admission_date,a.status " +
            "FROM admission a " +
            "INNER JOIN course c ON a.course_id=c.course_id " +
            "WHERE a.student_id=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, studentId);

            return ps.executeQuery();

        }

        catch(Exception e){

            e.printStackTrace();

        }

        return null;
    }
    public boolean addAdmission(int studentId, int courseId) {

        try {

            Connection con = DBConnection.getConnection();

            String sql = "INSERT INTO admission(student_id,course_id,admission_date,status) VALUES(?,?,CURDATE(),'Registered')";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, studentId);
            ps.setInt(2, courseId);

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (Exception e) {

            e.printStackTrace();

        }

        return false;
    }
 // Check if student is already registered for the course
    public boolean isAlreadyRegistered(int studentId, int courseId) {

        try {

            Connection con = DBConnection.getConnection();

            String sql = "SELECT * FROM admission WHERE student_id=? AND course_id=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, studentId);
            ps.setInt(2, courseId);

            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (Exception e) {

            e.printStackTrace();

        }

        return false;
    }
    public ResultSet getAllAdmissions() {

        try {

            Connection con = DBConnection.getConnection();

            String sql =
            "SELECT a.admission_id, s.name, c.course_name, " +
            "a.admission_date, a.status " +
            "FROM admission a " +
            "INNER JOIN student s ON a.student_id = s.student_id " +
            "INNER JOIN course c ON a.course_id = c.course_id";

            PreparedStatement ps = con.prepareStatement(sql);

            return ps.executeQuery();

        } catch(Exception e) {

            e.printStackTrace();

        }
        return null;
    }
    public ResultSet getRegisteredStudents() {

        try {

            Connection con = DBConnection.getConnection();

            String sql =
            "SELECT a.admission_id, s.student_id, s.name, s.email, " +
            "c.course_name, a.admission_date, a.status " +
            "FROM admission a " +
            "INNER JOIN student s ON a.student_id=s.student_id " +
            "INNER JOIN course c ON a.course_id=c.course_id";

            PreparedStatement ps = con.prepareStatement(sql);

            return ps.executeQuery();

        } catch(Exception e) {

            e.printStackTrace();

        }

        return null;
    }
    public ResultSet searchByCourse(String courseName){

        try{

            Connection con = DBConnection.getConnection();

            String sql =
            "SELECT a.admission_id,s.student_id,s.name,s.email," +
            "c.course_name,a.admission_date,a.status " +
            "FROM admission a " +
            "INNER JOIN student s ON a.student_id=s.student_id " +
            "INNER JOIN course c ON a.course_id=c.course_id " +
            "WHERE c.course_name LIKE ?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1,"%"+courseName+"%");

            return ps.executeQuery();

        }

        catch(Exception e){

            e.printStackTrace();

        }

        return null;

    }
    public boolean withdrawStudent(int admissionId){

        try{

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
            "DELETE FROM admission WHERE admission_id=?");

            ps.setInt(1, admissionId);

            return ps.executeUpdate()>0;

        }

        catch(Exception e){

            e.printStackTrace();

        }

        return false;

    }
}