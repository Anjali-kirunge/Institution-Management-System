package employee;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import dao.EmployeeDAO;
import model.Employee;
import main.HomePage;

public class EmployeeRegister extends JFrame implements ActionListener {

    JLabel l1,l2,l3,l4,l5;

    JTextField t1,t2,t3;

    JPasswordField p1,p2;

    JButton register,back;

    public EmployeeRegister() {

        setTitle("Employee Registration");
        setSize(500,500);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(new Color(204,255,204));

        l1=new JLabel("Name");
        l2=new JLabel("Email");
        l3=new JLabel("Phone");
        l4=new JLabel("Password");
        l5=new JLabel("Confirm Password");

        t1=new JTextField();
        t2=new JTextField();
        t3=new JTextField();

        p1=new JPasswordField();
        p2=new JPasswordField();

        register=new JButton("Register");
        back=new JButton("Back");

        l1.setBounds(50,50,150,25);
        l2.setBounds(50,100,150,25);
        l3.setBounds(50,150,150,25);
        l4.setBounds(50,200,150,25);
        l5.setBounds(50,250,150,25);

        t1.setBounds(220,50,200,25);
        t2.setBounds(220,100,200,25);
        t3.setBounds(220,150,200,25);
        p1.setBounds(220,200,200,25);
        p2.setBounds(220,250,200,25);

        register.setBounds(120,340,100,35);
        back.setBounds(260,340,100,35);

        add(l1); add(l2); add(l3); add(l4); add(l5);
        add(t1); add(t2); add(t3);
        add(p1); add(p2);
        add(register); add(back);

        register.addActionListener(this);
        back.addActionListener(this);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==register){

            String name=t1.getText();
            String email=t2.getText();
            String phone=t3.getText();
            String password=new String(p1.getPassword());
            String confirm=new String(p2.getPassword());

            if(name.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty()){

                JOptionPane.showMessageDialog(this,"Fill all fields");
                return;
            }

            if(!password.equals(confirm)){

                JOptionPane.showMessageDialog(this,"Passwords do not match");
                return;
            }

            Employee emp=new Employee(name,email,phone,password,"Pending");

            EmployeeDAO dao=new EmployeeDAO();

            if(dao.registerEmployee(emp)){

                JOptionPane.showMessageDialog(this,
                        "Registration Successful\nWaiting for Admin Approval");

                t1.setText("");
                t2.setText("");
                t3.setText("");
                p1.setText("");
                p2.setText("");

            }else{

                JOptionPane.showMessageDialog(this,"Registration Failed");
            }

        }

        if(e.getSource()==back){

            new HomePage();
            dispose();
        }

    }
}