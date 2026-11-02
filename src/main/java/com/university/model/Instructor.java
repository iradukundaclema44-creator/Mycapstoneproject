package com.university.model;

public class Instructor extends Person {

    private String department;
    private double salary;

    public Instructor(int id, String name, String email,
                      String department, double salary) {

        super(id, name, email);
        this.department = department;
        this.salary = salary;
    }

    public String getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Department: " + department);
        System.out.println("Salary: " + salary);
    }
}
