package student;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.AdmissionDAO;
import dao.CourseDAO;

public class ViewCourses extends JFrame implements ActionListener {

    JTable table;
    DefaultTableModel model;

    JButton register;
    JButton refresh;
    JButton back;

    CourseDAO courseDAO = new CourseDAO();
    AdmissionDAO admissionDAO = new AdmissionDAO();

    int selectedCourseId = -1;

    public ViewCourses() {

        setTitle("Available Courses");

        setSize(900,550);

        setLocationRelativeTo(null);

        setLayout(new BorderLayout());
        
        getContentPane().setBackground(new Color(204,255,204));

        model = new DefaultTableModel();

        model.setColumnIdentifiers(new String[]{

                "Course ID",
                "Course Name",
                "Duration",
                "Fees",
                "Status"

        });

        table = new JTable(model);

        JScrollPane scroll = new JScrollPane(table);

        add(scroll,BorderLayout.CENTER);

        JPanel panel = new JPanel(new FlowLayout());

        register = new JButton("Register Course");

        refresh = new JButton("Refresh");

        back = new JButton("Back");

        panel.add(register);
        panel.add(refresh);
        panel.add(back);

        add(panel,BorderLayout.SOUTH);

        register.addActionListener(this);

        refresh.addActionListener(this);

        back.addActionListener(this);
        // Row Selection
        table.getSelectionModel().addListSelectionListener(e -> {

            int row = table.getSelectedRow();

            if(row != -1){

                selectedCourseId = Integer.parseInt(
                        model.getValueAt(row,0).toString());

            }

        });

        loadCourses();

        setVisible(true);

    }

    // Load Courses from Database
    public void loadCourses(){

        model.setRowCount(0);

        try{

            ResultSet rs = courseDAO.getCourses();

            while(rs.next()){

                model.addRow(new Object[]{

                        rs.getInt("course_id"),
                        rs.getString("course_name"),
                        rs.getString("duration"),
                        rs.getDouble("fees"),
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

        // Register Course
        if(e.getSource() == register){

            if(selectedCourseId == -1){

                JOptionPane.showMessageDialog(this,
                        "Please Select a Course");

                return;
            }

            int row = table.getSelectedRow();

            String courseName = model.getValueAt(row, 1).toString();

            double fees = Double.parseDouble(
                    model.getValueAt(row, 3).toString());

            // Open Payment Form
            new PaymentForm(selectedCourseId, courseName, fees);

            dispose();
        }

        // Refresh
        else if(e.getSource() == refresh){

            loadCourses();

        }

        // Back
        else if(e.getSource() == back){

            new StudentDashboard();

            dispose();

        }

    }

}