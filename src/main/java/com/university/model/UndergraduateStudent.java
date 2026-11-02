package com.university.model;

public class UndergraduateStudent extends Student {

    private int yearOfStudy;

    public UndergraduateStudent(int id, String name, String email,
                                String studentNumber, String major,
                                int yearOfStudy) {

        super(id, name, email, studentNumber, major);
        this.yearOfStudy = yearOfStudy;
    }

    public int getYearOfStudy() {
        return yearOfStudy;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Year of Study: " + yearOfStudy);
    }
}
