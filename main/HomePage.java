package main;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import admin.AdminLogin;
import employee.EmployeeLogin;
import employee.EmployeeRegister;
import student.StudentLogin;
import student.StudentRegister;

public class HomePage extends JFrame implements ActionListener {

    JLabel title;

    JButton studentLogin, employeeLogin, adminLogin;
    JButton studentRegister, employeeRegister, exit;

    public HomePage() {

        setTitle("Institution Management System");
        setSize(600,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(new Color(204,255,204));

        title = new JLabel("INSTITUTION MANAGEMENT SYSTEM");
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setBounds(70,30,500,40);
        add(title);

        studentLogin = new JButton("Student Login");
        employeeLogin = new JButton("Employee Login");
        adminLogin = new JButton("Admin Login");

        studentRegister = new JButton("Register as Student");
        employeeRegister = new JButton("Register as Employee");

        exit = new JButton("Exit");

        studentLogin.setBounds(180,100,220,35);
        employeeLogin.setBounds(180,150,220,35);
        adminLogin.setBounds(180,200,220,35);

        studentRegister.setBounds(180,260,220,35);
        employeeRegister.setBounds(180,310,220,35);

        exit.setBounds(180,370,220,35);

        add(studentLogin);
        add(employeeLogin);
        add(adminLogin);

        add(studentRegister);
        add(employeeRegister);

        add(exit);

        studentLogin.addActionListener(this);
        employeeLogin.addActionListener(this);
        adminLogin.addActionListener(this);
        studentRegister.addActionListener(this);
        employeeRegister.addActionListener(this);
        exit.addActionListener(this);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==studentLogin){
            new StudentLogin();
            dispose();
        }

        else if(e.getSource()==employeeLogin){
            new EmployeeLogin();
            dispose();
        }

        else if(e.getSource()==adminLogin){
            new AdminLogin();
            dispose();
        }

        else if(e.getSource()==studentRegister){
            new StudentRegister();
            dispose();
        }

        else if(e.getSource()==employeeRegister){
            new EmployeeRegister();
            dispose();
        }

        else if(e.getSource()==exit){
            System.exit(0);
        }

    }

    public static void main(String[] args) {

        new HomePage();

    }

}