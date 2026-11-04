
package com.university.dao;

import com.university.model.Course;
import com.university.model.Instructor;
import com.university.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {

    // 1. Add Course
    public void addCourse(Course course) throws SQLException {

        String sql = "INSERT INTO courses "
                + "(course_name, credit_hours, instructor_id) "
                + "VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, course.getCourseName());
            stmt.setInt(2, course.getCreditHours());
            stmt.setInt(3, course.getInstructor().getId());

            stmt.executeUpdate();

            System.out.println("Course added successfully!");
        }
    }

    // 2. Get All Courses
    public List<Course> getAllCourses() throws SQLException {

        List<Course> courses = new ArrayList<>();

        String sql = "select * from courses";

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

                Course course = new Course(
                        rs.getInt("course_id"),
                        rs.getString("course_name"),
                        rs.getInt("credit_hours"),
                        instructor
                );

                courses.add(course);
            }
        }

        return courses;
    }

    // 3. Delete Course
    public void deleteCourse(int id) throws SQLException {

        String sql = "DELETE FROM courses WHERE course_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int rows = stmt.executeUpdate();

            if (rows > 0) {
                System.out.println("Course deleted successfully!");
            } else {
                System.out.println("Course not found!");
            }
        }
    }
}
