
package com.university.dao;

import com.university.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EnrollmentDAO {

    // 1. Enroll Student
    public void enrollStudent(int studentId, int courseId)
            throws SQLException {

        String sql = "INSERT INTO enrollments "
                + "(student_id, course_id) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, studentId);
            stmt.setInt(2, courseId);

            stmt.executeUpdate();

            System.out.println("Student enrolled successfully!");
        }
    }

    // 2. Update Enrollment
    public void updateEnrollment(int enrollmentId, int newCourseId)
            throws SQLException {

        String sql = "UPDATE enrollments "
                + "SET course_id = ? WHERE enrollment_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, newCourseId);
            stmt.setInt(2, enrollmentId);

            int rows = stmt.executeUpdate();

            if (rows > 0) {
                System.out.println("Enrollment updated successfully!");
            } else {
                System.out.println("Enrollment not found!");
            }
        }
    }

    // 3. Delete Enrollment
    public void deleteEnrollment(int enrollmentId)
            throws SQLException {

        String sql = "DELETE FROM enrollments WHERE enrollment_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, enrollmentId);

            int rows = stmt.executeUpdate();

            if (rows > 0) {
                System.out.println("Enrollment deleted successfully!");
            } else {
                System.out.println("Enrollment not found!");
            }
        }
    }

    // 4. Print Students With Their Courses
    public void printStudentsWithCourses() throws SQLException {

        String sql = "SELECT s.student_id, s.name, s.email, "
                + "c.course_id, c.course_name, c.credit_hours, "
                + "e.enrollment_id "
                + "FROM enrollments e "
                + "JOIN students s ON e.student_id = s.student_id "
                + "JOIN courses c ON e.course_id = c.course_id "
                + "ORDER BY s.student_id";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {


            while (rs.next()) {

                System.out.println("----------------------------");
                System.out.println("Enrollment ID: "
                        + rs.getInt("enrollment_id"));
                System.out.println("Student ID: "
                        + rs.getInt("student_id"));
                System.out.println("Student Name: "
                        + rs.getString("name"));
                System.out.println("Student Email: "
                        + rs.getString("email"));
                System.out.println("Course ID: "
                        + rs.getInt("course_id"));
                System.out.println("Course Name: "
                        + rs.getString("course_name"));
                System.out.println("Credit Hours: "
                        + rs.getInt("credit_hours"));
            }


            }
        }
}

