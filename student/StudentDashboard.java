package student;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import main.HomePage;

public class StudentDashboard extends JFrame implements ActionListener {

    JButton viewCourses;
    JButton myCourses;
    JButton payment;
    JButton changePassword;
    JButton logout;

    public StudentDashboard() {

        setTitle("Student Dashboard");
        setSize(600,500);
        setLayout(null);
        getContentPane().setBackground(new Color(204,255,204));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel title = new JLabel("STUDENT DASHBOARD");
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setBounds(170,20,300,30);
        add(title);

        viewCourses = new JButton("View Courses");
        myCourses = new JButton("My Courses");
        payment = new JButton("Payment");
        changePassword = new JButton("Change Password");
        logout = new JButton("Logout");

        viewCourses.setBounds(180,80,220,35);
        myCourses.setBounds(180,140,220,35);
        payment.setBounds(180,200,220,35);
        changePassword.setBounds(180,260,220,35);
        logout.setBounds(180,320,220,35);

        add(viewCourses);
        add(myCourses);
        add(payment);
        add(changePassword);
        add(logout);

        viewCourses.addActionListener(this);
        myCourses.addActionListener(this);
        payment.addActionListener(this);
        changePassword.addActionListener(this);
        logout.addActionListener(this);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==viewCourses){

            new ViewCourses();
            dispose();

        }

        else if(e.getSource()==myCourses){

            new MyCourses();
            dispose();

        }

        else if(e.getSource()==payment){

            JOptionPane.showMessageDialog(this,
                    "Please select a course first from View Courses.");

        }
        else if(e.getSource()==changePassword){

            new StudentChangePassword();
            dispose();

        }

        else if(e.getSource()==logout){

            new HomePage();
            dispose();

        }
    }

}