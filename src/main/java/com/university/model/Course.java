package com.university.model;

public class Course {

    private int courseId;
    private String courseName;
    private int creditHours;
    private Instructor instructor;

    public Course(int courseId, String courseName,
                  int creditHours, Instructor instructor) {

        this.courseId = courseId;
        this.courseName = courseName;
        this.creditHours = creditHours;
        this.instructor = instructor;
    }

    public int getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getCreditHours() {
        return creditHours;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void displayCourseInfo() {
        System.out.println("Course ID: " + courseId);
        System.out.println("Course Name: " + courseName);
        System.out.println("Credit Hours: " + creditHours);

        if (instructor != null) {
            System.out.println("Instructor: " + instructor.getName());
        }
    }
}