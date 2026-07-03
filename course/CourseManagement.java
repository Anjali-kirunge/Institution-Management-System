package course;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import dao.CourseDAO;
import employee.EmployeeDashboard;
import model.Course;

public class CourseManagement extends JFrame implements ActionListener{

    JLabel l1,l2,l3;

    JTextField txtName,txtDuration,txtFees;

    JTable table;
    
    int selectedCourseId = -1;

    DefaultTableModel model;

    JButton add,update,delete,search,refresh,back;

    CourseDAO dao=new CourseDAO();

    public CourseManagement(){

        setTitle("Course Management");

        setSize(900,600);

        setLocationRelativeTo(null);

        setLayout(null);
        
        getContentPane().setBackground(new Color(204,255,204));

        JLabel title=new JLabel("COURSE MANAGEMENT");

        title.setFont(new Font("Arial",Font.BOLD,22));

        title.setBounds(300,20,300,30);

        add(title);

        l1=new JLabel("Course Name");

        l2=new JLabel("Duration");

        l3=new JLabel("Fees");

        txtName=new JTextField();

        txtDuration=new JTextField();

        txtFees=new JTextField();

        l1.setBounds(40,80,120,25);
        txtName.setBounds(150,80,200,25);

        l2.setBounds(40,120,120,25);
        txtDuration.setBounds(150,120,200,25);

        l3.setBounds(40,160,120,25);
        txtFees.setBounds(150,160,200,25);

        add(l1);
        add(l2);
        add(l3);

        add(txtName);
        add(txtDuration);
        add(txtFees);

        add=new JButton("Add");

        update=new JButton("Update");

        delete=new JButton("Delete");

        search=new JButton("Search");

        refresh=new JButton("Refresh");

        back=new JButton("Back");

        add.setBounds(400,80,120,30);

        update.setBounds(550,80,120,30);

        delete.setBounds(700,80,120,30);

        search.setBounds(400,130,120,30);

        refresh.setBounds(550,130,120,30);

        back.setBounds(700,130,120,30);

        add(add);
        add(update);
        add(delete);
        add(search);
        add(refresh);
        add(back);

        model=new DefaultTableModel();

        model.setColumnIdentifiers(new String[]{

                "Course ID",
                "Course Name",
                "Duration",
                "Fees",
                "Status"

        });

        table=new JTable(model);
        
        table.getSelectionModel().addListSelectionListener(e -> {

            int row = table.getSelectedRow();

            if(row != -1){

                selectedCourseId = Integer.parseInt(
                        model.getValueAt(row,0).toString());

                txtName.setText(
                        model.getValueAt(row,1).toString());

                txtDuration.setText(
                        model.getValueAt(row,2).toString());

                txtFees.setText(
                        model.getValueAt(row,3).toString());

            }

        });

        JScrollPane sp=new JScrollPane(table);

        sp.setBounds(20,230,840,300);

        add(sp);

        add.addActionListener(this);

        update.addActionListener(this);

        delete.addActionListener(this);

        search.addActionListener(this);

        refresh.addActionListener(this);

        back.addActionListener(this);

        loadCourses();

        setVisible(true);

    }

    public void loadCourses(){

        model.setRowCount(0);

        try{

            ResultSet rs=dao.getCourses();

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
    public void actionPerformed(ActionEvent e){
    	// ADD COURSE
    	if(e.getSource() == add) {

    	    try {

    	        String courseName = txtName.getText().trim();
    	        String duration = txtDuration.getText().trim();
    	        String feeText = txtFees.getText().trim();

    	        // Validation
    	        if(courseName.isEmpty() || duration.isEmpty() || feeText.isEmpty()) {

    	            JOptionPane.showMessageDialog(this,
    	                    "Please fill all fields.");

    	            return;
    	        }

    	        double fees = Double.parseDouble(feeText);

    	        // Create Course Object
    	        Course course = new Course();

    	        course.setCourseName(courseName);
    	        course.setDuration(duration);
    	        course.setFees(fees);
    	        course.setStatus("Available");

    	        // Save to Database
    	        boolean status = dao.addCourse(course);

    	        if(status) {

    	            JOptionPane.showMessageDialog(this,
    	                    "Course Added Successfully!");

    	            // Clear Fields
    	            txtName.setText("");
    	            txtDuration.setText("");
    	            txtFees.setText("");

    	            // Reload JTable
    	            loadCourses();

    	        } else {

    	            JOptionPane.showMessageDialog(this,
    	                    "Failed to Add Course.");

    	        }

    	    } catch(NumberFormatException ex) {

    	        JOptionPane.showMessageDialog(this,
    	                "Fees must be a numeric value.");

    	    } catch(Exception ex) {

    	        ex.printStackTrace();

    	    }

    	}
        // UPDATE
        if(e.getSource()==update){

            if(selectedCourseId==-1){

                JOptionPane.showMessageDialog(this,"Select Course First");
                return;

            }

            dao.updateCourse(
                    selectedCourseId,
                    txtName.getText(),
                    txtDuration.getText(),
                    Double.parseDouble(txtFees.getText())
            );

            JOptionPane.showMessageDialog(this,"Course Updated Successfully");

            loadCourses();
        }

        // DELETE
        if(e.getSource()==delete){

            if(selectedCourseId==-1){

                JOptionPane.showMessageDialog(this,"Select Course");
                return;

            }

            int option = JOptionPane.showConfirmDialog(
                    this,
                    "Delete this course?",
                    "Confirm",
                    JOptionPane.YES_NO_OPTION);

            if(option==JOptionPane.YES_OPTION){

                dao.deleteCourse(selectedCourseId);

                JOptionPane.showMessageDialog(this,"Course Deleted");

                loadCourses();
            }
        }

        // SEARCH
        if(e.getSource()==search){

            model.setRowCount(0);

            try{

                ResultSet rs = dao.searchCourse(txtName.getText());

                while(rs.next()){

                    model.addRow(new Object[]{

                            rs.getInt("course_id"),
                            rs.getString("course_name"),
                            rs.getString("duration"),
                            rs.getDouble("fees"),
                            rs.getString("status")

                    });

                }

            }catch(Exception ex){

                ex.printStackTrace();

            }

        }

        // REFRESH
        if(e.getSource()==refresh){

            loadCourses();

        }

        // BACK
        if(e.getSource()==back){

            new EmployeeDashboard();

            dispose();

        }

    }

}