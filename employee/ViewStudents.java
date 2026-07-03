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

public class ViewStudents extends JFrame implements ActionListener {

    JTable table;

    DefaultTableModel model;

    JTextField txtSearch;

    JButton search;
    JButton refresh;
    JButton back;

    AdmissionDAO dao = new AdmissionDAO();

    public ViewStudents() {

        setTitle("Registered Students");

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

        txtSearch = new JTextField(15);

        search = new JButton("Search");

        refresh = new JButton("Refresh");

        back = new JButton("Back");

        panel.add(new JLabel("Course"));

        panel.add(txtSearch);

        panel.add(search);

        panel.add(refresh);

        panel.add(back);

        add(panel,BorderLayout.SOUTH);

        search.addActionListener(this);

        refresh.addActionListener(this);

        back.addActionListener(this);

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

    public void searchCourse(){

        model.setRowCount(0);

        try{

            ResultSet rs = dao.searchByCourse(txtSearch.getText());

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

        if(e.getSource()==search){

            searchCourse();

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