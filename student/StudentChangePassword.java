package student;

import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;

import dao.StudentDAO;

public class StudentChangePassword extends JFrame implements ActionListener {

    JLabel l1,l2,l3;

    JPasswordField oldPass,newPass,confirmPass;

    JButton change,back;

    StudentDAO dao = new StudentDAO();

    public StudentChangePassword(){

        setTitle("Change Password");

        setSize(450,350);

        setLayout(null);
        
        getContentPane().setBackground(new Color(204,255,204));

        setLocationRelativeTo(null);

        l1 = new JLabel("Old Password");
        l2 = new JLabel("New Password");
        l3 = new JLabel("Confirm Password");

        oldPass = new JPasswordField();
        newPass = new JPasswordField();
        confirmPass = new JPasswordField();

        change = new JButton("Change Password");
        back = new JButton("Back");

        l1.setBounds(40,50,120,25);
        oldPass.setBounds(180,50,180,25);

        l2.setBounds(40,100,120,25);
        newPass.setBounds(180,100,180,25);

        l3.setBounds(40,150,120,25);
        confirmPass.setBounds(180,150,180,25);

        change.setBounds(70,230,140,35);
        back.setBounds(230,230,100,35);

        add(l1);
        add(l2);
        add(l3);

        add(oldPass);
        add(newPass);
        add(confirmPass);

        add(change);
        add(back);

        change.addActionListener(this);
        back.addActionListener(this);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e){

        if(e.getSource()==change){

            String oldPassword = new String(oldPass.getPassword());
            String newPassword = new String(newPass.getPassword());
            String confirmPassword = new String(confirmPass.getPassword());

            if(oldPassword.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()){

                JOptionPane.showMessageDialog(this,
                        "Please fill all fields");

                return;
            }

            if(!newPassword.equals(confirmPassword)){

                JOptionPane.showMessageDialog(this,
                        "New Password and Confirm Password do not match");

                return;
            }

            boolean status = dao.changePassword(
                    StudentSession.studentId,
                    oldPassword,
                    newPassword);

            if(status){

                JOptionPane.showMessageDialog(this,
                        "Password Changed Successfully");

                new StudentDashboard();

                dispose();

            }else{

                JOptionPane.showMessageDialog(this,
                        "Old Password is Incorrect");

            }

        }

        if(e.getSource()==back){

            new StudentDashboard();

            dispose();

        }

    }

}