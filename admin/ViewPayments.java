package admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import dao.FinanceDAO;

public class ViewPayments extends JFrame implements ActionListener {

    JTable table;
    DefaultTableModel model;

    JButton refresh, back;

    FinanceDAO dao = new FinanceDAO();

    public ViewPayments() {

        setTitle("View Payments");

        setSize(1000,500);

        setLocationRelativeTo(null);

        setLayout(new BorderLayout());
        
        getContentPane().setBackground(new Color(204,255,204));

        model = new DefaultTableModel();

        model.setColumnIdentifiers(new String[]{

                "Payment ID",
                "Student Name",
                "Course",
                "Actual Amount",
                "Discount",
                "Final Amount",
                "Promo Code",
                "Payment Date",
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

        loadPayments();

        setVisible(true);

    }

    public void loadPayments() {

        model.setRowCount(0);

        try {

            ResultSet rs = dao.getAllPayments();

            while(rs.next()) {

                model.addRow(new Object[]{

                        rs.getInt("payment_id"),
                        rs.getString("name"),
                        rs.getString("course_name"),
                        rs.getDouble("actual_amount"),
                        rs.getDouble("discount"),
                        rs.getDouble("final_amount"),
                        rs.getString("promo_code"),
                        rs.getDate("payment_date"),
                        rs.getString("payment_status")

                });

            }

        } catch(Exception e) {

            e.printStackTrace();

        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==refresh){

            loadPayments();

        }

        if(e.getSource()==back){

            new AdminDashboard();

            dispose();

        }

    }

}