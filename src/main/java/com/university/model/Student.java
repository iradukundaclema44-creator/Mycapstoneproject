package com.university.model;

public class Student extends Person {

    private String studentNumber;
    private String major;

    public Student(int id, String name, String email,
                   String studentNumber, String major) {

        super(id, name, email);

        this.studentNumber = studentNumber;
        this.major = major;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public String getMajor() {
        return major;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Student Number: " + studentNumber);
        System.out.println("Major: " + major);
    }
}
