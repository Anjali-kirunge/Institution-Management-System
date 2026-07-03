package admin;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import main.HomePage;

public class AdminDashboard extends JFrame implements ActionListener{

    JButton approveStudent;

    JButton approveEmployee;
    
    JButton viewPayments;
    
    JButton viewAdmissions;

    JButton logout;

    public AdminDashboard() {

        setTitle("Admin Dashboard");

        setSize(500,400);

        setLocationRelativeTo(null);

        setLayout(null);
        
        getContentPane().setBackground(new Color(204,255,204));

        JLabel title=new JLabel("ADMIN DASHBOARD");

        title.setFont(new Font("Arial",Font.BOLD,22));

        title.setBounds(120,20,300,30);

        add(title);

        approveStudent=new JButton("Approve Students");

        approveEmployee=new JButton("Approve Employees");
        
        viewPayments = new JButton("View Payments");
        
        viewAdmissions = new JButton("view Admissions");

        logout=new JButton("Logout");

        approveStudent.setBounds(130, 80, 220, 35);

        approveEmployee.setBounds(130, 140, 220, 35);

        viewPayments.setBounds(130, 200, 220, 35);

        viewAdmissions.setBounds(130,260,220,35);

        logout.setBounds(130, 320, 220, 35);
        add(approveStudent);

        add(approveEmployee);
        
        add(viewPayments);
        
        add(viewAdmissions);

        add(logout);

        approveStudent.addActionListener(this);

        approveEmployee.addActionListener(this);
        
        viewPayments.addActionListener(this);
        
        viewAdmissions.addActionListener(this);
        
        logout.addActionListener(this);

        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==approveStudent){

            new ApproveStudent();

            dispose();

        }

        if(e.getSource()==approveEmployee){

            new ApproveEmployee();

            dispose();

        }
        
        else if(e.getSource()==viewPayments){

            new ViewPayments();

            dispose();

        }

        else if(e.getSource()==viewAdmissions){

            new ViewAdmissions();

            dispose();

        }
        

        if(e.getSource()==logout){

            new HomePage();

            dispose();

        }

    }

}