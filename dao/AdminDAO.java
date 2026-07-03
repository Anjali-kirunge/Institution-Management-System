package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import database.DBConnection;

public class AdminDAO {

    public boolean login(String username,String password) {

        try {

            Connection con = DBConnection.getConnection();

            String sql="select * from admin where username=? and password=?";

            PreparedStatement ps=con.prepareStatement(sql);

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs=ps.executeQuery();

            if(rs.next())
                return true;

        }
        catch(Exception e) {
            e.printStackTrace();
        }

        return false;
    }
   
}