package admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import dao.AdmissionDAO;

public class ViewAdmissions extends JFrame implements ActionListener {

    JTable table;
    DefaultTableModel model;

    JButton refresh, back;

    AdmissionDAO dao = new AdmissionDAO();

    public ViewAdmissions() {

        setTitle("View Admissions");

        setSize(900,500);

        setLocationRelativeTo(null);

        setLayout(new BorderLayout());
        
        getContentPane().setBackground(new Color(204,255,204));

        model = new DefaultTableModel();

        model.setColumnIdentifiers(new String[]{

                "Admission ID",
                "Student Name",
                "Course",
                "Admission Date",
                "Status"

        });

        table = new JTable(model);

        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel panel = new JPanel();

        refresh = new JButton("Refresh");
        back = new JButton("Back");

        panel.add(refresh);
        panel.add(back);

        add(panel, BorderLayout.SOUTH);

        refresh.addActionListener(this);
        back.addActionListener(this);

        loadAdmissions();

        setVisible(true);

    }

    public void loadAdmissions() {

        model.setRowCount(0);

        try {

            ResultSet rs = dao.getAllAdmissions();

            while(rs.next()) {

                model.addRow(new Object[]{

                        rs.getInt("admission_id"),
                        rs.getString("name"),
                        rs.getString("course_name"),
                        rs.getDate("admission_date"),
                        rs.getString("status")

                });

            }

        } catch(Exception e) {

            e.printStackTrace();

        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==refresh){

            loadAdmissions();

        }

        else if(e.getSource()==back){

            new AdminDashboard();

            dispose();

        }

    }

}