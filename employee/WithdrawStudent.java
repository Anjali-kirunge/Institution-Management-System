package employee;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import dao.AdmissionDAO;

public class WithdrawStudent extends JFrame implements ActionListener {

    JTable table;

    DefaultTableModel model;

    JButton withdraw;
    JButton refresh;
    JButton back;

    AdmissionDAO dao = new AdmissionDAO();

    int admissionId = -1;

    public WithdrawStudent() {

        setTitle("Withdraw Student");

        setSize(950,550);

        setLocationRelativeTo(null);

        setLayout(new BorderLayout());
        
        getContentPane().setBackground(new Color(204,255,204));

        model = new DefaultTableModel();

        model.setColumnIdentifiers(new String[]{

                "Admission ID",
                "Student ID",
                "Student Name",
                "Email",
                "Course",
                "Admission Date",
                "Status"

        });

        table = new JTable(model);

        JScrollPane scroll = new JScrollPane(table);

        add(scroll,BorderLayout.CENTER);

        JPanel panel = new JPanel(new FlowLayout());

        withdraw = new JButton("Withdraw");

        refresh = new JButton("Refresh");

        back = new JButton("Back");

        panel.add(withdraw);
        panel.add(refresh);
        panel.add(back);

        add(panel,BorderLayout.SOUTH);

        withdraw.addActionListener(this);
        refresh.addActionListener(this);
        back.addActionListener(this);

        table.getSelectionModel().addListSelectionListener(e -> {

            int row = table.getSelectedRow();

            if(row != -1){

                admissionId = Integer.parseInt(
                        model.getValueAt(row,0).toString());

            }

        });

        loadStudents();

        setVisible(true);

    }

    public void loadStudents(){

        model.setRowCount(0);

        try{

            ResultSet rs = dao.getRegisteredStudents();

            while(rs.next()){

                model.addRow(new Object[]{

                        rs.getInt("admission_id"),
                        rs.getInt("student_id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("course_name"),
                        rs.getDate("admission_date"),
                        rs.getString("status")

                });

            }

        }

        catch(Exception e){

            e.printStackTrace();

        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==withdraw){

            if(admissionId==-1){

                JOptionPane.showMessageDialog(this,
                        "Please Select a Student");

                return;

            }

            int option = JOptionPane.showConfirmDialog(

                    this,

                    "Are you sure you want to withdraw this student?",

                    "Confirm Withdrawal",

                    JOptionPane.YES_NO_OPTION

            );

            if(option==JOptionPane.YES_OPTION){

                boolean status = dao.withdrawStudent(admissionId);

                if(status){

                    JOptionPane.showMessageDialog(this,
                            "Student Withdrawn Successfully");

                    loadStudents();

                    admissionId = -1;

                }

                else{

                    JOptionPane.showMessageDialog(this,
                            "Withdrawal Failed");

                }

            }

        }

        else if(e.getSource()==refresh){

            loadStudents();

        }

        else if(e.getSource()==back){

            new EmployeeDashboard();

            dispose();

        }

    }

}