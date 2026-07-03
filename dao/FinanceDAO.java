package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import database.DBConnection;
import model.Finance;

public class FinanceDAO {

    // Save Payment
    public boolean savePayment(Finance finance) {

        try {

            Connection con = DBConnection.getConnection();

            String sql = "INSERT INTO finance(student_id,course_id,actual_amount,discount,final_amount,promo_code,payment_date,payment_status) VALUES(?,?,?,?,?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, finance.getStudentId());
            ps.setInt(2, finance.getCourseId());
            ps.setDouble(3, finance.getActualAmount());
            ps.setDouble(4, finance.getDiscount());
            ps.setDouble(5, finance.getFinalAmount());
            ps.setString(6, finance.getPromoCode());
            ps.setDate(7, finance.getPaymentDate());
            ps.setString(8, finance.getPaymentStatus());

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (Exception e) {

            e.printStackTrace();

        }

        return false;
    }

    // Check if payment already exists
    public boolean isPaymentDone(int studentId, int courseId) {

        try {

            Connection con = DBConnection.getConnection();

            String sql = "SELECT * FROM finance WHERE student_id=? AND course_id=?";

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
    public ResultSet getAllPayments() {

        try {

            Connection con = DBConnection.getConnection();

            String sql =
            "SELECT f.payment_id, s.name, c.course_name, " +
            "f.actual_amount, f.discount, f.final_amount, " +
            "f.promo_code, f.payment_date, f.payment_status " +
            "FROM finance f " +
            "INNER JOIN student s ON f.student_id = s.student_id " +
            "INNER JOIN course c ON f.course_id = c.course_id";

            PreparedStatement ps = con.prepareStatement(sql);

            return ps.executeQuery();

        } catch(Exception e) {

            e.printStackTrace();

        }

        return null;
    }

}