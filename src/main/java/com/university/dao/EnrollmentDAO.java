package com.university.dao;
import com.university.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EnrollmentDAO {

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
}