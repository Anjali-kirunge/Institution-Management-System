package student;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import dao.AdmissionDAO;

public class MyCourses extends JFrame implements ActionListener{

    JTable table;

    DefaultTableModel model;

    JButton refresh,back;

    AdmissionDAO dao=new AdmissionDAO();

    public MyCourses(){

        setTitle("My Courses");

        setSize(800,500);

        setLocationRelativeTo(null);

        setLayout(new BorderLayout());
        
        getContentPane().setBackground(new Color(204,255,204));

        model=new DefaultTableModel();

        model.setColumnIdentifiers(new String[]{

                "Course Name",
                "Duration",
                "Fees",
                "Admission Date",
                "Status"

        });

        table=new JTable(model);

        JScrollPane sp=new JScrollPane(table);

        add(sp,BorderLayout.CENTER);

        JPanel p=new JPanel();

        refresh=new JButton("Refresh");

        back=new JButton("Back");

        p.add(refresh);

        p.add(back);

        add(p,BorderLayout.SOUTH);

        refresh.addActionListener(this);

        back.addActionListener(this);

        loadCourses();

        setVisible(true);

    }

    public void loadCourses(){

        model.setRowCount(0);

        try{

            ResultSet rs=dao.getMyCourses(StudentSession.studentId);

            while(rs.next()){

                model.addRow(new Object[]{

                        rs.getString("course_name"),

                        rs.getString("duration"),

                        rs.getDouble("fees"),

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
    public void actionPerformed(ActionEvent e){

        if(e.getSource()==refresh){

            loadCourses();

        }

        if(e.getSource()==back){

            new StudentDashboard();

            dispose();

        }

    }

}