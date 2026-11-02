package com.university.model;

public class GraduateStudent extends Student {

    private String researchTopic;

    public GraduateStudent(int id, String name, String email,
                           String studentNumber, String major,
                           String researchTopic) {

        super(id, name, email, studentNumber, major);
        this.researchTopic = researchTopic;
    }

    public String getResearchTopic() {
        return researchTopic;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Research Topic: " + researchTopic);
    }
}
