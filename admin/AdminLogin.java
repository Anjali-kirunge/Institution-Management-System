package admin;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import dao.AdminDAO;
import main.HomePage;

public class AdminLogin extends JFrame implements ActionListener{

    JLabel l1,l2;

    JTextField t1;

    JPasswordField p1;

    JButton login,back;

    public AdminLogin() {

        setTitle("Admin Login");

        setSize(400,300);

        setLocationRelativeTo(null);

        setLayout(null);
        
        getContentPane().setBackground(new Color(204,255,204));

        l1=new JLabel("Username");

        l2=new JLabel("Password");

        t1=new JTextField();

        p1=new JPasswordField();

        login=new JButton("Login");

        back=new JButton("Back");

        l1.setBounds(50,50,100,25);
        l2.setBounds(50,100,100,25);

        t1.setBounds(170,50,150,25);
        p1.setBounds(170,100,150,25);

        login.setBounds(80,180,100,35);
        back.setBounds(200,180,100,35);

        add(l1);
        add(l2);
        add(t1);
        add(p1);
        add(login);
        add(back);

        login.addActionListener(this);
        back.addActionListener(this);

        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==login) {

            String username=t1.getText();

            String password=new String(p1.getPassword());

            AdminDAO dao=new AdminDAO();

            if(dao.login(username,password)) {

                JOptionPane.showMessageDialog(this,"Login Successful");

                new AdminDashboard();

                dispose();

            }

            else {

                JOptionPane.showMessageDialog(this,"Invalid Username or Password");

            }

        }

        if(e.getSource()==back) {

            new HomePage();

            dispose();

        }

    }

}