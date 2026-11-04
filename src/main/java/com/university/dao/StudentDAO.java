
package com.university.dao;

import com.university.model.Student;
import com.university.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    // 1. Add Student
    public void addStudent(Student student) throws SQLException {

        String sql = "INSERT INTO students "
                + "(name, email, student_number, major) "
                + "VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, student.getName());
            stmt.setString(2, student.getEmail());
            stmt.setString(3, student.getStudentNumber());
            stmt.setString(4, student.getMajor());

            stmt.executeUpdate();

            System.out.println("Student added successfully!");
        }
    }

    // 2. Get All Students
    public List<Student> getAllStudents() throws SQLException {

        List<Student> students = new ArrayList<>();

        String sql = "SELECT * FROM students";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {

                Student student = new Student(
                        rs.getInt("student_id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("student_number"),
                        rs.getString("major")
                );

                students.add(student);
            }
        }

        return students;
    }

    // 3. Delete Student
    public void deleteStudent(int id) throws SQLException {

        String sql = "DELETE FROM students WHERE student_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int rows = stmt.executeUpdate();

            if (rows > 0) {
                System.out.println("Student deleted successfully!");
            } else {
                System.out.println("Student not found!");
            }
        }
    }
}
