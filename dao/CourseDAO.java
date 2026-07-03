package dao;

import java.sql.*;
import database.DBConnection;
import model.Course;

public class CourseDAO {

    public boolean addCourse(Course c){

        try{

            Connection con=DBConnection.getConnection();

            String sql="insert into course(course_name,duration,fees,status) values(?,?,?,?)";

            PreparedStatement ps=con.prepareStatement(sql);

            ps.setString(1,c.getCourseName());

            ps.setString(2,c.getDuration());

            ps.setDouble(3,c.getFees());

            ps.setString(4,c.getStatus());

            int rows=ps.executeUpdate();

            return rows>0;

        }

        catch(Exception e){

            e.printStackTrace();

        }

        return false;

    }
    public ResultSet searchCourse(String name){

        try{

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM course WHERE course_name LIKE ?");

            ps.setString(1,"%"+name+"%");

            return ps.executeQuery();

        }

        catch(Exception e){

            e.printStackTrace();

        }

        return null;

    }

    public ResultSet getCourses(){

        try{

            Connection con=DBConnection.getConnection();

            PreparedStatement ps=con.prepareStatement("select * from course");

            return ps.executeQuery();

        }

        catch(Exception e){

            e.printStackTrace();

        }

        return null;

    }

    public void deleteCourse(int id){

        try{

            Connection con=DBConnection.getConnection();

            PreparedStatement ps=con.prepareStatement("delete from course where course_id=?");

            ps.setInt(1,id);

            ps.executeUpdate();

        }

        catch(Exception e){

            e.printStackTrace();

        }

    }

    public void updateCourse(int id,String name,String duration,double fees){

        try{

            Connection con=DBConnection.getConnection();

            PreparedStatement ps=con.prepareStatement(
                    "update course set course_name=?,duration=?,fees=? where course_id=?");

            ps.setString(1,name);

            ps.setString(2,duration);

            ps.setDouble(3,fees);

            ps.setInt(4,id);

            ps.executeUpdate();

        }

        catch(Exception e){

            e.printStackTrace();

        }

    }

}