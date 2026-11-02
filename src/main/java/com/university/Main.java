
package com.university;

// Import DAO classes to communicate with the database.
import com.university.dao.StudentDAO;
import com.university.dao.InstructorDAO;
import com.university.dao.CourseDAO;

// Import model classes to create objects.
import com.university.model.Student;
import com.university.model.Instructor;
import com.university.model.Course;

// Import classes for database errors and user input.
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {

    // Scanner receives input from the keyboard.
    static Scanner scanner = new Scanner(System.in);

    // Create DAO objects to perform database operations.
    static StudentDAO studentDAO = new StudentDAO();
    static InstructorDAO instructorDAO = new InstructorDAO();
    static CourseDAO courseDAO = new CourseDAO();

    // Main method: the program starts here.
    public static void main(String[] args) {

        int choice;

        // Display the main menu until the user chooses Exit.
        do {
            System.out.println("\n===== UNIVERSITY MANAGEMENT SYSTEM =====");
            System.out.println("1. Student Management");
            System.out.println("2. Instructor Management");
            System.out.println("3. Course Management");
            System.out.println("4. Exit");

            // Get the user's menu choice.
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            // Select an operation according to the user's choice.
            switch (choice) {

                case 1:
                    studentMenu();
                    break;

                case 2:
                    instructorMenu();
                    break;

                case 3:
                    courseMenu();
                    break;

                case 4:
                    System.out.println("Thank you for using the system!");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 4);

        // Close Scanner when the program ends.
        scanner.close();
    }

    // ================= STUDENT MANAGEMENT =================

    // This method manages student CRUD operations.
    public static void studentMenu() {

        int choice;

        // Repeat the menu until the user chooses Back.
        do {
            System.out.println("\n===== STUDENT MANAGEMENT =====");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Back to Main Menu");

            // Receive the user's choice.
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            // Handle database operations and SQL errors.
            try {
                switch (choice) {

                    case 1:
                        // Collect student information.
                        System.out.print("Enter Name: ");
                        String name = scanner.nextLine();

                        System.out.print("Enter Email: ");
                        String email = scanner.nextLine();

                        System.out.print("Enter Student Number: ");
                        String number = scanner.nextLine();

                        System.out.print("Enter Major: ");
                        String major = scanner.nextLine();

                        // Create a new Student object.
                        Student student = new Student(
                                0, name, email, number, major
                        );

                        // Save the student in the database.
                        studentDAO.addStudent(student);
                        break;

                    case 2:
                        // Retrieve all students from the database.
                        List<Student> students = studentDAO.getAllStudents();

                        // Display each student.
                        for (Student s : students) {
                            s.displayInfo();
                            System.out.println("------------------");
                        }
                        break;

                    case 3:
                        // Search for a student using student ID.
                        System.out.print("Enter Student ID: ");
                        int searchId = scanner.nextInt();

                        Student found = studentDAO.getStudentById(searchId);

                        if (found != null) {
                            found.displayInfo();
                        } else {
                            System.out.println("Student not found!");
                        }
                        break;

                    case 4:
                        // Get the ID of the student to update.
                        System.out.print("Enter Student ID: ");
                        int updateId = scanner.nextInt();
                        scanner.nextLine();

                        // Check whether the student exists.
                        Student oldStudent =
                                studentDAO.getStudentById(updateId);

                        if (oldStudent != null) {

                            // Collect the new student information.
                            System.out.print("Enter New Name: ");
                            String newName = scanner.nextLine();

                            System.out.print("Enter New Email: ");
                            String newEmail = scanner.nextLine();

                            System.out.print("Enter New Student Number: ");
                            String newNumber = scanner.nextLine();

                            System.out.print("Enter New Major: ");
                            String newMajor = scanner.nextLine();

                            // Create an updated Student object.
                            Student updated = new Student(
                                    updateId, newName, newEmail,
                                    newNumber, newMajor
                            );

                            // Update student information in the database.
                            studentDAO.updateStudent(updated);

                        } else {
                            System.out.println("Student not found!");
                        }
                        break;

                    case 5:
                        // Delete a student using student ID.
                        System.out.print("Enter Student ID: ");
                        int deleteId = scanner.nextInt();

                        studentDAO.deleteStudent(deleteId);
                        break;

                    case 6:
                        // Return to the main menu.
                        System.out.println("Returning to main menu...");
                        break;

                    default:
                        System.out.println("Invalid choice!");
                }

            } catch (SQLException e) {
                // Display database errors.
                System.out.println("Database error: " + e.getMessage());
            }

        } while (choice != 6);
    }

    // ================= INSTRUCTOR MANAGEMENT =================

    // This method manages instructor CRUD operations.
    public static void instructorMenu() {

        int choice;

        // Repeat the menu until the user chooses Back.
        do {
            System.out.println("\n===== INSTRUCTOR MANAGEMENT =====");
            System.out.println("1. Add Instructor");
            System.out.println("2. View All Instructors");
            System.out.println("3. Search Instructor");
            System.out.println("4. Update Instructor");
            System.out.println("5. Delete Instructor");
            System.out.println("6. Back to Main Menu");

            // Receive the user's choice.
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (choice) {

                    case 1:
                        // Collect instructor information.
                        System.out.print("Enter Name: ");
                        String name = scanner.nextLine();

                        System.out.print("Enter Email: ");
                        String email = scanner.nextLine();

                        System.out.print("Enter Department: ");
                        String department = scanner.nextLine();

                        System.out.print("Enter Salary: ");
                        double salary = scanner.nextDouble();
                        scanner.nextLine();

                        // Create a new Instructor object.
                        Instructor instructor = new Instructor(
                                0, name, email, department, salary
                        );

                        // Save the instructor in the database.
                        instructorDAO.addInstructor(instructor);
                        break;

                    case 2:
                        // Retrieve all instructors.
                        List<Instructor> instructors =
                                instructorDAO.getAllInstructors();

                        // Display each instructor.
                        for (Instructor i : instructors) {
                            i.displayInfo();
                            System.out.println("------------------");
                        }
                        break;

                    case 3:
                        // Search for an instructor using ID.
                        System.out.print("Enter Instructor ID: ");
                        int searchId = scanner.nextInt();

                        Instructor found =
                                instructorDAO.getInstructorById(searchId);

                        if (found != null) {
                            found.displayInfo();
                        } else {
                            System.out.println("Instructor not found!");
                        }
                        break;

                    case 4:
                        // Get the instructor ID to update.
                        System.out.print("Enter Instructor ID: ");
                        int updateId = scanner.nextInt();
                        scanner.nextLine();

                        // Check whether the instructor exists.
                        Instructor oldInstructor =
                                instructorDAO.getInstructorById(updateId);

                        if (oldInstructor != null) {

                            // Collect the new instructor information.
                            System.out.print("Enter New Name: ");
                            String newName = scanner.nextLine();

                            System.out.print("Enter New Email: ");
                            String newEmail = scanner.nextLine();

                            System.out.print("Enter New Department: ");
                            String newDepartment = scanner.nextLine();

                            System.out.print("Enter New Salary: ");
                            double newSalary = scanner.nextDouble();
                            scanner.nextLine();

                            // Create an updated Instructor object.
                            Instructor updated = new Instructor(
                                    updateId, newName, newEmail,
                                    newDepartment, newSalary
                            );

                            // Update instructor information in the database.
                            instructorDAO.updateInstructor(updated);

                        } else {
                            System.out.println("Instructor not found!");
                        }
                        break;

                    case 5:
                        // Delete an instructor using ID.
                        System.out.print("Enter Instructor ID: ");
                        int deleteId = scanner.nextInt();

                        instructorDAO.deleteInstructor(deleteId);
                        break;

                    case 6:
                        // Return to the main menu.
                        System.out.println("Returning to main menu...");
                        break;

                    default:
                        System.out.println("Invalid choice!");
                }

            } catch (SQLException e) {
                // Handle database errors.
                System.out.println("Database error: " + e.getMessage());
            }

        } while (choice != 6);
    }

    // ================= COURSE MANAGEMENT =================

    // This method manages course CRUD operations.
    public static void courseMenu() {

        int choice;

        // Repeat the menu until the user chooses Back.
        do {
            System.out.println("\n===== COURSE MANAGEMENT =====");
            System.out.println("1. Add Course");
            System.out.println("2. View All Courses");
            System.out.println("3. Search Course");
            System.out.println("4. Update Course");
            System.out.println("5. Delete Course");
            System.out.println("6. Back to Main Menu");

            // Receive the user's choice.
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (choice) {

                    case 1:
                        // Collect course information.
                        System.out.print("Enter Course Name: ");
                        String courseName = scanner.nextLine();

                        System.out.print("Enter Credit Hours: ");
                        int creditHours = scanner.nextInt();

                        System.out.print("Enter Instructor ID: ");
                        int instructorId = scanner.nextInt();
                        scanner.nextLine();

                        // Find the instructor assigned to this course.
                        Instructor courseInstructor =
                                instructorDAO.getInstructorById(instructorId);

                        if (courseInstructor != null) {

                            // Create a Course object with its instructor.
                            Course course = new Course(
                                    0, courseName,
                                    creditHours, courseInstructor
                            );

                            // Save the course in the database.
                            courseDAO.addCourse(course);

                        } else {
                            System.out.println("Instructor not found!");
                        }
                        break;

                    case 2:
                        // Retrieve all courses from the database.
                        List<Course> courses = courseDAO.getAllCourses();

                        // Display each course and its instructor.
                        for (Course c : courses) {
                            c.displayCourseInfo();
                            System.out.println("------------------");
                        }
                        break;

                    case 3:
                        // Search for a course using course ID.
                        System.out.print("Enter Course ID: ");
                        int searchId = scanner.nextInt();

                        Course found = courseDAO.getCourseById(searchId);

                        if (found != null) {
                            found.displayCourseInfo();
                        } else {
                            System.out.println("Course not found!");
                        }
                        break;

                    case 4:
                        // Get the course ID to update.
                        System.out.print("Enter Course ID: ");
                        int updateId = scanner.nextInt();
                        scanner.nextLine();

                        // Check whether the course exists.
                        Course oldCourse =
                                courseDAO.getCourseById(updateId);

                        if (oldCourse != null) {

                            // Collect the new course information.
                            System.out.print("Enter New Course Name: ");
                            String newName = scanner.nextLine();

                            System.out.print("Enter New Credit Hours: ");
                            int newHours = scanner.nextInt();

                            System.out.print("Enter New Instructor ID: ");
                            int newInstructorId = scanner.nextInt();
                            scanner.nextLine();

                            // Find the new instructor.
                            Instructor newInstructor =
                                    instructorDAO.getInstructorById(
                                            newInstructorId
                                    );

                            if (newInstructor != null) {

                                // Create a Course object with updated data.
                                Course updated = new Course(
                                        updateId, newName,
                                        newHours, newInstructor
                                );

                                // Update the course in the database.
                                courseDAO.updateCourse(updated);

                            } else {
                                System.out.println("Instructor not found!");
                            }

                        } else {
                            System.out.println("Course not found!");
                        }
                        break;

                    case 5:
                        // Delete a course using course ID.
                        System.out.print("Enter Course ID: ");
                        int deleteId = scanner.nextInt();

                        courseDAO.deleteCourse(deleteId);
                        break;

                    case 6:
                        // Return to the main menu.
                        System.out.println("Returning to main menu...");
                        break;

                    default:
                        System.out.println("Invalid choice!");
                }

            } catch (SQLException e) {
                // Display database errors.
                System.out.println("Database error: " + e.getMessage());
            }

        } while (choice != 6);
    }
}
