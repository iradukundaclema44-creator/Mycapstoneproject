
package com.university.dao;

import com.university.model.Instructor;
import com.university.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InstructorDAO {

    // 1. Add Instructor
    public void addInstructor(Instructor instructor)
            throws SQLException {

        String sql = "INSERT INTO instructors "
                + "(name, email, department, salary) "
                + "VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, instructor.getName());
            stmt.setString(2, instructor.getEmail());
            stmt.setString(3, instructor.getDepartment());
            stmt.setDouble(4, instructor.getSalary());

            stmt.executeUpdate();

            System.out.println("Instructor added successfully!");
        }
    }

    // 2. Get All Instructors
    public List<Instructor> getAllInstructors()
            throws SQLException {

        List<Instructor> instructors = new ArrayList<>();

        String sql = "SELECT * FROM instructors";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {

                Instructor instructor = new Instructor(
                        rs.getInt("instructor_id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("department"),
                        rs.getDouble("salary")
                );

                instructors.add(instructor);
            }
        }

        return instructors;
    }

    // 5. Delete Instructor
    public void deleteInstructor(int id)
            throws SQLException {

        String sql = "DELETE FROM instructors WHERE instructor_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int rows = stmt.executeUpdate();

            if (rows > 0) {
                System.out.println("Instructor deleted successfully!");
            } else {
                System.out.println("Instructor not found!");
            }
        }
    }
}
