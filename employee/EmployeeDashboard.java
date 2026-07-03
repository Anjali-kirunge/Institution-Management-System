package employee;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import main.HomePage;
import course.CourseManagement;

public class EmployeeDashboard extends JFrame implements ActionListener{

    JButton addCourse;
    JButton updateCourse;
    JButton deleteCourse;
    JButton searchCourse;
    JButton viewStudents;
    JButton withdrawStudent;
    JButton changePassword;
    JButton logout;

    public EmployeeDashboard(){

        setTitle("Employee Dashboard");

        setSize(650,500);

        setLocationRelativeTo(null);

        setLayout(null);
        
        getContentPane().setBackground(new Color(204,255,204));

        JLabel title = new JLabel("EMPLOYEE DASHBOARD");

        title.setFont(new Font("Arial",Font.BOLD,22));

        title.setBounds(180,20,350,30);

        add(title);

        addCourse = new JButton("Add Course");
        updateCourse = new JButton("Update Course");
        deleteCourse = new JButton("Delete Course");
        searchCourse = new JButton("Search Course");
        viewStudents = new JButton("View Students");
        withdrawStudent = new JButton("Withdraw Student");
        changePassword = new JButton("Change Password");
        logout = new JButton("Logout");

        addCourse.setBounds(60,90,220,35);
        updateCourse.setBounds(340,90,220,35);

        deleteCourse.setBounds(60,150,220,35);
        searchCourse.setBounds(340,150,220,35);

        viewStudents.setBounds(60,210,220,35);
        withdrawStudent.setBounds(340,210,220,35);

        changePassword.setBounds(60,270,220,35);
        logout.setBounds(340,270,220,35);

        add(addCourse);
        add(updateCourse);
        add(deleteCourse);
        add(searchCourse);
        add(viewStudents);
        add(withdrawStudent);
        add(changePassword);
        add(logout);

        addCourse.addActionListener(this);
        updateCourse.addActionListener(this);
        deleteCourse.addActionListener(this);
        searchCourse.addActionListener(this);
        viewStudents.addActionListener(this);
        withdrawStudent.addActionListener(this);
        changePassword.addActionListener(this);
        logout.addActionListener(this);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==addCourse){

            new CourseManagement();
            dispose();

        }

        else if(e.getSource()==updateCourse){

            new CourseManagement();
            dispose();

        }

        else if(e.getSource()==deleteCourse){

            new CourseManagement();
            dispose();

        }

        else if(e.getSource()==searchCourse){

            new CourseManagement();
            dispose();

        }

        else if(e.getSource()==viewStudents){

            new ViewStudents();

            dispose();

        }

        else if(e.getSource()==withdrawStudent){

            new WithdrawStudent();

            dispose();

        }

        else if(e.getSource()==changePassword){

            new EmployeeChangePassword();

            dispose();

        }

        else if(e.getSource()==logout){

            new HomePage();

            dispose();

        }

    }

}