
package com.university;

import com.university.dao.StudentDAO;
import com.university.dao.InstructorDAO;
import com.university.dao.CourseDAO;

import com.university.model.Student;
import com.university.model.Instructor;
import com.university.model.Course;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        StudentDAO studentDAO = new StudentDAO();
        InstructorDAO instructorDAO = new InstructorDAO();
        CourseDAO courseDAO = new CourseDAO();

        while (true) {

            printMenu();

            try {
                int choice = Integer.parseInt(sc.nextLine().trim());

                switch (choice) {

                    // ADD STUDENT
                    case 1 -> {
                        System.out.print("Name: ");
                        String name = sc.nextLine().trim();

                        System.out.print("Email: ");
                        String email = sc.nextLine().trim();

                        System.out.print("Student Number: ");
                        String number = sc.nextLine().trim();

                        System.out.print("Major: ");
                        String major = sc.nextLine().trim();

                        if (name.isEmpty() || email.isEmpty()
                                || number.isEmpty() || major.isEmpty()) {
                            System.out.println("All fields are required.");
                        } else {
                            Student student = new Student(
                                    0, name, email, number, major
                            );

                            studentDAO.addStudent(student);
                        }
                    }

                    // VIEW STUDENTS
                    case 2 -> {
                        for (Student s : studentDAO.getAllStudents()) {
                            s.displayInfo();
                            System.out.println("------------------");
                        }
                    }

                    // DELETE STUDENT
                    case 3 -> {
                        System.out.print("Student ID: ");
                        int id = Integer.parseInt(sc.nextLine().trim());

                        studentDAO.deleteStudent(id);
                    }

                    // ADD INSTRUCTOR
                    case 4 -> {
                        System.out.print("Name: ");
                        String name = sc.nextLine().trim();

                        System.out.print("Email: ");
                        String email = sc.nextLine().trim();

                        System.out.print("Department: ");
                        String department = sc.nextLine().trim();

                        System.out.print("Salary: ");
                        double salary = Double.parseDouble(sc.nextLine().trim());

                        if (name.isEmpty() || email.isEmpty()
                                || department.isEmpty() || salary < 0) {
                            System.out.println("Enter valid instructor details.");
                        } else {
                            Instructor instructor = new Instructor(
                                    0, name, email, department, salary
                            );

                            instructorDAO.addInstructor(instructor);
                        }
                    }

                    // VIEW INSTRUCTORS
                    case 5 -> {
                        for (Instructor i : instructorDAO.getAllInstructors()) {
                            i.displayInfo();
                            System.out.println("------------------");
                        }
                    }

                    // DELETE INSTRUCTOR
                    case 6 -> {
                        System.out.print("Instructor ID: ");
                        int id = Integer.parseInt(sc.nextLine().trim());

                        instructorDAO.deleteInstructor(id);
                    }

                    // ADD COURSE
                    case 7 -> {
                        System.out.println("Adding a course requires an Instructor object.");
                        System.out.println("The current simplified InstructorDAO does not provide getInstructorById().");
                        System.out.println("Add Course is temporarily unavailable.");
                    }

                    // VIEW COURSES
                    case 8 -> {
                        for (Course c : courseDAO.getAllCourses()) {
                            c.displayCourseInfo();
                            System.out.println("------------------");
                        }
                    }

                    // DELETE COURSE
                    case 9 -> {
                        System.out.print("Course ID: ");
                        int id = Integer.parseInt(sc.nextLine().trim());

                        courseDAO.deleteCourse(id);
                    }

                    // EXIT
                    case 0 -> {
                        System.out.println("Thank you for using the system!");
                        sc.close();
                        return;
                    }

                    default -> System.out.println("Invalid menu choice.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            } catch (SQLException e) {
                System.out.println("Database error: " + e.getMessage());
            }
        }
    }

    private static void printMenu() {
        System.out.println("""
                
                ===== UNIVERSITY MANAGEMENT SYSTEM =====
                1. Add Student
                2. View All Students
                3. Delete Student
                4. Add Instructor
                5. View All Instructors
                6. Delete Instructor
                7. Add Course (temporarily unavailable)
                8. View All Courses
                9. Delete Course
                0. Exit
                """);

        System.out.print("Choose: ");
    }
}
