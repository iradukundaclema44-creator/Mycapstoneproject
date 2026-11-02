package com.university;

import com.university.util.DatabaseConnection;
import java.sql.Connection;

public class Main {

    public static void main(String[] args) {

        System.out.println("UNIVERSITY STUDENT MANAGEMENT SYSTEM");

        Connection connection = DatabaseConnection.getConnection();

        if (connection != null) {
            System.out.println("Application started successfully!");

            try {
                connection.close();
            } catch (Exception e) {
                System.out.println("Error closing connection.");
            }
        } else {
            System.out.println("Please check your database connection.");
        }
    }
}