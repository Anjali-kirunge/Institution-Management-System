package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import database.DBConnection;

public class PromoCodeDAO {

    public double getDiscount(String promoCode) {

        try {

            Connection con = DBConnection.getConnection();

            String sql = "SELECT discount FROM promocode WHERE code=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, promoCode);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){

                return rs.getDouble("discount");

            }

        } catch(Exception e){

            e.printStackTrace();

        }

        return -1;

    }

}