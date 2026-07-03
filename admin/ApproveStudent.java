package admin;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import dao.StudentDAO;

public class ApproveStudent extends JFrame implements ActionListener{

    JTable table;

    DefaultTableModel model;

    JButton approve,refresh,back;

    StudentDAO dao=new StudentDAO();

    public ApproveStudent(){

        setTitle("Approve Students");

        setSize(700,400);

        setLocationRelativeTo(null);

        setLayout(new BorderLayout());
        
        getContentPane().setBackground(new Color(204,255,204));

        model=new DefaultTableModel();

        model.setColumnIdentifiers(new String[]{
                "ID","Name","Email","Phone","Status"
        });

        table=new JTable(model);

        JScrollPane sp=new JScrollPane(table);

        add(sp,BorderLayout.CENTER);

        JPanel p=new JPanel();

        approve=new JButton("Approve");

        refresh=new JButton("Refresh");

        back=new JButton("Back");

        p.add(approve);

        p.add(refresh);

        p.add(back);

        add(p,BorderLayout.SOUTH);

        approve.addActionListener(this);

        refresh.addActionListener(this);

        back.addActionListener(this);

        loadStudents();

        setVisible(true);

    }

    void loadStudents(){

        model.setRowCount(0);

        try{

            ResultSet rs=dao.getPendingStudents();

            while(rs.next()){

                model.addRow(new Object[]{

                        rs.getInt("student_id"),

                        rs.getString("name"),

                        rs.getString("email"),

                        rs.getString("phone"),

                        rs.getString("status")

                });

            }

        }catch(Exception e){

            e.printStackTrace();

        }

    }

    @Override
    public void actionPerformed(ActionEvent e){

        if(e.getSource()==approve){

            int row=table.getSelectedRow();

            if(row==-1){

                JOptionPane.showMessageDialog(this,"Select Student");

                return;

            }

            int id=(int)model.getValueAt(row,0);

            dao.approveStudent(id);

            JOptionPane.showMessageDialog(this,"Student Approved");

            loadStudents();

        }

        if(e.getSource()==refresh){

            loadStudents();

        }

        if(e.getSource()==back){

            new AdminDashboard();

            dispose();

        }

    }

}