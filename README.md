# 🎓 Institution Management System

A desktop-based **Institution Management System** developed using **Java Swing**, **JDBC**, and **MySQL**. The application automates the management of students, employees, courses, admissions, and finance through secure role-based access.

---

## 📌 Project Overview

The Institution Management System is designed to simplify and automate institutional operations by replacing manual record management with a centralized database-driven application.

The system provides separate portals for:

- 👨‍🎓 Student
- 👨‍💼 Employee
- 👨‍💻 Admin

Each user has specific functionalities based on their role.

---

## 🚀 Features

### 👨‍🎓 Student Module

- Student Registration
- Student Login
- View Available Courses
- Course Registration
- Promo Code Validation
- Course Fee Payment
- My Courses
- Change Password

---

### 👨‍💼 Employee Module

- Employee Registration
- Employee Login
- Add Course
- Update Course
- Delete Course
- Search Course
- View Registered Students
- Withdraw Student Admission
- Change Password

---

### 👨‍💻 Admin Module

- Admin Login
- Approve Student Registration
- Approve Employee Registration
- View Payments
- View Admissions
- Manage Courses
- Reset Student Password
- Reset Employee Password

---

### 💳 Finance Module

- Course Fee Payment
- Promo Code Discount
- Payment History
- Payment Validation
- Automatic Admission After Successful Payment

---

## 🗂 Database Tables

- Admin
- Student
- Employee
- Course
- Admission
- Finance
- PromoCode

---

## 🛠 Technologies Used

- Java
- Java Swing
- JDBC
- MySQL
- SQL
- Eclipse IDE
- MySQL Workbench

---

## 🏗 Project Structure

```
InstitutionManagementSystem
│
├── src
│   ├── admin
│   ├── course
│   ├── dao
│   ├── database
│   ├── employee
│   ├── main
│   ├── model
│   └── student
│
├── mysql-connector.jar
└── README.md
```

---

## 🔄 Project Workflow

```
Home Page
      │
      ▼
Registration / Login
      │
      ▼
Admin Approval
      │
      ▼
Student / Employee Login
      │
      ▼
Dashboard
      │
      ▼
Course Management
      │
      ▼
Payment with Promo Code
      │
      ▼
Admission
      │
      ▼
Reports
```

---

## 🔑 User Roles

### Student

- Register
- Login
- View Courses
- Register for Courses
- Make Payment
- Apply Promo Code
- View My Courses
- Change Password

---

### Employee

- Login
- Manage Courses
- View Students
- Search Students
- Withdraw Student Admission
- Change Password

---

### Admin

- Login
- Approve Students
- Approve Employees
- View Payments
- View Admissions
- Reset Passwords

---

## 📸 Screenshots

Add screenshots of:

- Home Page
- <img width="747" height="622" alt="image" src="https://github.com/user-attachments/assets/37a345d7-e962-4dc5-a57b-cd71b0b417e5" />
- Student Registration
- <img width="593" height="622" alt="image" src="https://github.com/user-attachments/assets/6353f3d4-7b02-46cf-9476-e88bba987499" />
- Student Login
- <img width="535" height="438" alt="image" src="https://github.com/user-attachments/assets/88e6ca30-1e4e-4668-9d86-9b8c75836ff3" />
- Employee Login
- <img width="462" height="370" alt="image" src="https://github.com/user-attachments/assets/43d681c2-17fe-49bb-b2d4-7f63fd5b378d" />
- Admin Login
- <img width="480" height="373" alt="image" src="https://github.com/user-attachments/assets/fe114b85-f0bf-44aa-a735-22a5843bc819" />
- Student Dashboard
- <img width="737" height="610" alt="image" src="https://github.com/user-attachments/assets/67f6b52c-c53b-429d-80d4-a9a5dd23ca05" />
- Employee Dashboard
- <img width="786" height="602" alt="image" src="https://github.com/user-attachments/assets/8d03aa12-f2c8-45f7-a126-ccf133735163" />
- Admin Dashboard
- <img width="617" height="506" alt="image" src="https://github.com/user-attachments/assets/24ce107c-31ef-4a4c-946b-3189c98e8fe0" />
- Course Management
- <img width="1102" height="740" alt="image" src="https://github.com/user-attachments/assets/f02436e7-2438-4adb-96f4-1f815645d2c0" />
- Payment Module
- <img width="737" height="623" alt="image" src="https://github.com/user-attachments/assets/b70a8f49-9180-40d2-bdd3-2e735d2f1c0d" />
- View Payments
- <img width="1212" height="227" alt="image" src="https://github.com/user-attachments/assets/94df755e-f866-4084-9f75-10262060de64" />
- View Admissions
- <img width="1097" height="115" alt="image" src="https://github.com/user-attachments/assets/41388531-86d6-4413-b0b5-83bb31074e45" />
- Database codes-
- CREATE DATABASE institution_db;
USE institution_db;
CREATE TABLE student(
student_id INT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(100),
email VARCHAR(100),
phone VARCHAR(15),
password VARCHAR(100),
status VARCHAR(20)
);

CREATE TABLE employee(
employee_id INT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(100),
email VARCHAR(100),
phone VARCHAR(15),
password VARCHAR(100),
status VARCHAR(20)
);

CREATE TABLE admin(
admin_id INT PRIMARY KEY AUTO_INCREMENT,
username VARCHAR(50),
password VARCHAR(50)
);

INSERT INTO admin(username,password)
VALUES('admin','admin123');

CREATE TABLE course(
course_id INT PRIMARY KEY AUTO_INCREMENT,

course_name VARCHAR(100),
duration VARCHAR(50),
fees DOUBLE,
status VARCHAR(20)
);
CREATE TABLE admission(
admission_id INT PRIMARY KEY AUTO_INCREMENT,
student_id INT,
course_id INT,
status VARCHAR(20)
);

CREATE TABLE finance(
payment_id INT PRIMARY KEY AUTO_INCREMENT,
student_id INT,
course_id INT,
actual_amount DOUBLE,
discount DOUBLE,
paid_amount DOUBLE,
promo_code VARCHAR(50)
);

INSERT INTO promocode(code,discount)
VALUES
('JAVA50',50),
('WELCOME20',20),
('AI100',1000);


select * from student;
select * from employee;
USE institution_db;
select * from admission;

SELECT * FROM admin;
DROP TABLE IF EXISTS admission;
CREATE TABLE admission(
    admission_id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT,
    course_id INT,
    admission_date DATE,
    status VARCHAR(30)
);
DROP TABLE IF EXISTS finance;
CREATE TABLE finance(
    payment_id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT,
    course_id INT,
    actual_amount DOUBLE,
    discount DOUBLE,
    final_amount DOUBLE,
    promo_code VARCHAR(30),
    payment_date DATE,
    payment_status VARCHAR(20)
);

CREATE TABLE promocode(
promo_id INT PRIMARY KEY AUTO_INCREMENT,
code VARCHAR(50),
discount DOUBLE
);
INSERT INTO promocode(code,discount)
VALUES
('JAVA50',5000),
('WELCOME20',2000),
('NEW1000',1000),
('CSE25',2500);
SELECT * FROM promocode;   
---
## 💡 Key Concepts Implemented

- Object-Oriented Programming (OOP)
- Java Swing GUI
- Event Handling
- JDBC Connectivity
- MySQL Database
- CRUD Operations
- PreparedStatement
- Role-Based Authentication
- Database Relationships
- Exception Handling

---

## 🔮 Future Enhancements

- Online Payment Gateway
- Email Notifications
- SMS Notifications
- Attendance Management
- Examination Module
- Result Management
- QR Code Payments
- Mobile Application
- Cloud Deployment
- AI-Based Student Analytics

---

## 👩‍💻 Developed By

**Anjali Kirunge**

Final Year Computer Science Engineering Student

---

## 📜 License

This project is developed for educational and learning purposes.
