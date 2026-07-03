package employee;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import dao.EmployeeDAO;
import main.HomePage;

public class EmployeeLogin extends JFrame implements ActionListener{

    JLabel l1,l2;

    JTextField email;

    JPasswordField password;

    JButton login,back;

    public EmployeeLogin(){

        setTitle("Employee Login");

        setSize(400,300);

        setLocationRelativeTo(null);

        setLayout(null);
        
        getContentPane().setBackground(new Color(204,255,204));

        l1=new JLabel("Email");

        l2=new JLabel("Password");

        email=new JTextField();

        password=new JPasswordField();

        login=new JButton("Login");

        back=new JButton("Back");

        l1.setBounds(50,60,100,25);

        l2.setBounds(50,110,100,25);

        email.setBounds(160,60,180,25);

        password.setBounds(160,110,180,25);

        login.setBounds(70,190,100,35);

        back.setBounds(210,190,100,35);

        add(l1);
        add(l2);
        add(email);
        add(password);
        add(login);
        add(back);

        login.addActionListener(this);
        back.addActionListener(this);

        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e){

        if(e.getSource()==login){

            EmployeeDAO dao = new EmployeeDAO();

            boolean status = dao.loginEmployee(

                    email.getText(),

                    new String(password.getPassword())

            );

            if(status){

                EmployeeSession.employeeId = dao.getEmployeeId(email.getText());

                JOptionPane.showMessageDialog(this,"Login Successful");

                new EmployeeDashboard();

                dispose();

            }

            else{

                JOptionPane.showMessageDialog(this,

                        "Invalid Login OR Waiting For Admin Approval");

            }

        }

        if(e.getSource()==back){

            new HomePage();

            dispose();

        }

    }

}