package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import database.DBConnection;

public class Admission {

    private int admissionId;
    private int studentId;
    private int courseId;
    private Date admissionDate;
    private String status;

    public Admission() {}

    public Admission(int studentId,int courseId,Date admissionDate,String status){

        this.studentId=studentId;
        this.courseId=courseId;
        this.admissionDate=admissionDate;
        this.status=status;

    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public Date getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(Date admissionDate) {
        this.admissionDate = admissionDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}