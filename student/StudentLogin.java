package student;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import dao.StudentDAO;
import main.HomePage;

public class StudentLogin extends JFrame implements ActionListener {

    JLabel l1, l2, title;

    JTextField txtEmail;

    JPasswordField txtPassword;

    JButton btnLogin, btnBack;

    StudentDAO dao = new StudentDAO();

    public StudentLogin() {

        setTitle("Student Login");
        setSize(450,350);
        setLayout(null);
        getContentPane().setBackground(new Color(204,255,204));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        title = new JLabel("STUDENT LOGIN");
        title.setFont(new Font("Arial", Font.BOLD, 22));

        l1 = new JLabel("Email");
        l2 = new JLabel("Password");

        txtEmail = new JTextField();
        txtPassword = new JPasswordField();

        btnLogin = new JButton("Login");
        btnBack = new JButton("Back");

        title.setBounds(120,20,250,30);

        l1.setBounds(50,80,100,25);
        txtEmail.setBounds(170,80,200,25);

        l2.setBounds(50,130,100,25);
        txtPassword.setBounds(170,130,200,25);

        btnLogin.setBounds(80,220,100,35);
        btnBack.setBounds(220,220,100,35);

        add(title);
        add(l1);
        add(l2);
        add(txtEmail);
        add(txtPassword);
        add(btnLogin);
        add(btnBack);

        btnLogin.addActionListener(this);
        btnBack.addActionListener(this);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==btnLogin){

            String email = txtEmail.getText().trim();
            String password = new String(txtPassword.getPassword());

            if(email.isEmpty() || password.isEmpty()){

                JOptionPane.showMessageDialog(this,
                        "Please enter Email and Password.");

                return;
            }

            boolean status = dao.loginStudent(email, password);

            if(status){

                StudentSession.studentId = dao.getStudentId(email);
                StudentSession.studentName = dao.getStudentName(email);
                StudentSession.studentEmail = email;

                JOptionPane.showMessageDialog(this,
                        "Login Successful");

                new StudentDashboard();

                dispose();

            }else{

                JOptionPane.showMessageDialog(this,
                        "Invalid Login or Waiting for Admin Approval.");

            }

        }

        if(e.getSource()==btnBack){

            new HomePage();
            dispose();

        }

    }

}