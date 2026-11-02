package com.university.collection;
import com.university.model.Student;
import com.university.model.Course;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Map;

public class UniversityRegistry {

    private List<Course> courses = new ArrayList<>();

    private Set<String> majors = new HashSet<>();

    private Map<Integer, Student> students = new HashMap<>();

    public void addStudent(Student student) {
        students.put(student.getId(), student);
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void addMajor(String major) {
        majors.add(major);
    }

    public void displayStudents() {
        for (Student student : students.values()) {
            student.displayInfo();
        }
    }
}