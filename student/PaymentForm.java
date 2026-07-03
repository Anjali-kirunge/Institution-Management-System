package student;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.*;

import dao.AdmissionDAO;
import dao.FinanceDAO;
import dao.PromoCodeDAO;
import model.Finance;

public class PaymentForm extends JFrame implements ActionListener {

    JLabel l1, l2, l3, l4, l5;

    JTextField txtCourse;
    JTextField txtActualAmount;
    JTextField txtPromo;
    JTextField txtDiscount;
    JTextField txtFinalAmount;

    JButton apply;
    JButton pay;
    JButton back;

    PromoCodeDAO promoDAO = new PromoCodeDAO();
    FinanceDAO financeDAO = new FinanceDAO();
    AdmissionDAO admissionDAO = new AdmissionDAO();

    int courseId;
    double actualAmount;
    double discount;
    double finalAmount;

    public PaymentForm(int courseId, String courseName, double fees) {

        this.courseId = courseId;
        this.actualAmount = fees;
        this.finalAmount = fees;

        setTitle("Payment");
        setSize(600,500);
        setLayout(null);
        getContentPane().setBackground(new Color(204,255,204));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        l1 = new JLabel("Course");
        l2 = new JLabel("Actual Amount");
        l3 = new JLabel("Promo Code");
        l4 = new JLabel("Discount");
        l5 = new JLabel("Final Amount");

        txtCourse = new JTextField();
        txtActualAmount = new JTextField();
        txtPromo = new JTextField();
        txtDiscount = new JTextField();
        txtFinalAmount = new JTextField();

        txtCourse.setEditable(false);
        txtActualAmount.setEditable(false);
        txtDiscount.setEditable(false);
        txtFinalAmount.setEditable(false);

        txtCourse.setText(courseName);
        txtActualAmount.setText(String.valueOf(fees));
        txtDiscount.setText("0");
        txtFinalAmount.setText(String.valueOf(fees));

        apply = new JButton("Apply");
        pay = new JButton("Pay");
        back = new JButton("Back");

        l1.setBounds(50,50,120,25);
        txtCourse.setBounds(220,50,250,25);

        l2.setBounds(50,100,120,25);
        txtActualAmount.setBounds(220,100,250,25);

        l3.setBounds(50,150,120,25);
        txtPromo.setBounds(220,150,150,25);
        apply.setBounds(390,150,80,25);

        l4.setBounds(50,200,120,25);
        txtDiscount.setBounds(220,200,250,25);

        l5.setBounds(50,250,120,25);
        txtFinalAmount.setBounds(220,250,250,25);

        pay.setBounds(150,350,100,35);
        back.setBounds(300,350,100,35);

        add(l1);
        add(txtCourse);

        add(l2);
        add(txtActualAmount);

        add(l3);
        add(txtPromo);

        add(apply);

        add(l4);
        add(txtDiscount);

        add(l5);
        add(txtFinalAmount);

        add(pay);
        add(back);

        apply.addActionListener(this);
        pay.addActionListener(this);
        back.addActionListener(this);

        setVisible(true);
    }

    private void applyPromo() {

        String promo = txtPromo.getText().trim();

        if(promo.isEmpty()) {

            JOptionPane.showMessageDialog(this,"Enter Promo Code");
            return;

        }

        discount = promoDAO.getDiscount(promo);

        if(discount==-1) {

            JOptionPane.showMessageDialog(this,"Invalid Promo Code");

            discount = 0;
            finalAmount = actualAmount;

        }
        else {

            finalAmount = actualAmount - discount;

            if(finalAmount<0)
                finalAmount = 0;

            JOptionPane.showMessageDialog(this,
                    "Promo Code Applied Successfully");
        }

        txtDiscount.setText(String.valueOf(discount));
        txtFinalAmount.setText(String.valueOf(finalAmount));

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // Apply Promo Code
        if (e.getSource() == apply) {

            applyPromo();

        }

        else if (e.getSource() == pay) {

            Finance finance = new Finance();

            finance.setStudentId(StudentSession.studentId);
            finance.setCourseId(courseId);
            finance.setActualAmount(actualAmount);
            finance.setDiscount(discount);
            finance.setFinalAmount(finalAmount);
            finance.setPromoCode(txtPromo.getText());
            finance.setPaymentDate(new Date(System.currentTimeMillis()));
            finance.setPaymentStatus("Paid");

            // Check duplicate payment
            if(financeDAO.isPaymentDone(StudentSession.studentId, courseId)){

                JOptionPane.showMessageDialog(this,
                        "Payment already completed for this course.");

                return;
            }

            // Check duplicate admission
            if(admissionDAO.isAlreadyRegistered(StudentSession.studentId, courseId)){

                JOptionPane.showMessageDialog(this,
                        "You are already registered for this course.");

                return;
            }

            // Save payment only once
            boolean payment = financeDAO.savePayment(finance);

            if(payment){

                boolean admission = admissionDAO.addAdmission(
                        StudentSession.studentId,
                        courseId);

                if(admission){

                    JOptionPane.showMessageDialog(this,
                            "Payment Successful\nCourse Registered Successfully");

                }else{

                    JOptionPane.showMessageDialog(this,
                            "Payment Saved\nAdmission Failed");

                }

                new StudentDashboard();
                dispose();

            }else{

                JOptionPane.showMessageDialog(this,
                        "Payment Failed");

            }

        }

        // Back Button
        else if (e.getSource() == back) {

            new StudentDashboard();
            dispose();

        }
      } 
    }