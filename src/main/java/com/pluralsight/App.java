package com.pluralsight;

import java.sql.*;
import java.util.Scanner;

    public class App {
        public static void main(String[] args) {

            try (
                    Connection connection = DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/northwind",
                            "root",
                            "yearup24"
                    )
            ) {
                Scanner scanner = new Scanner(System.in);
                int option = -1;

                while (option != 0) {
                    System.out.println("What do you want to do?");
                    System.out.println("1) Display all products");
                    System.out.println("2) Display all customers");
                    System.out.println("0) Exit");
                    System.out.print("Select an option: ");

                    option = scanner.nextInt();
                    scanner.nextLine(); // clear newline

                    if (option == 1) {
                        displayProducts(connection);
                    }
                    else if (option == 2) {
                        displayCustomers(connection);
                    }
                    else if (option == 0) {
                        System.out.println("Goodbye!");
                    }
                    else {
                        System.out.println("Invalid option.");
                    }
                }

            } catch (SQLException e) {
                System.out.println("ERROR: " + e.getMessage());
            }
        }


        private static void displayProducts(Connection connection) throws SQLException {

            String query = "SELECT ProductID, ProductName, UnitPrice, UnitsInStock FROM products";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                int id = resultSet.getInt("ProductID");
                String name = resultSet.getString("ProductName");
                double price = resultSet.getDouble("UnitPrice");
                int stock = resultSet.getInt("UnitsInStock");

                System.out.println("Id: " + id);
                System.out.println("Name: " + name);
                System.out.println("Price: " + price);
                System.out.println("Stock: " + stock);
                System.out.println("------------------");
            }
        }

        private static void displayCustomers(Connection connection) throws SQLException {

            String query = "SELECT ContactName, CompanyName, City, Country, Phone " +
                    "FROM customers ORDER BY Country";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                String contact = resultSet.getString("ContactName");
                String company = resultSet.getString("CompanyName");
                String city = resultSet.getString("City");
                String country = resultSet.getString("Country");
                String phone = resultSet.getString("Phone");

                System.out.println("Contact: " + contact);
                System.out.println("Company: " + company);
                System.out.println("City: " + city);
                System.out.println("Country: " + country);
                System.out.println("Phone: " + phone);
                System.out.println("------------------");
            }
        }
    }