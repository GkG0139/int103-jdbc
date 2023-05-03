package com.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class App {

  private static String insertId = "";

  public static void main(String[] args) {
    try {
      insertStudent();
      updateStudent();
      queryStudentData();
      deleteStudent();
      preparedStatementInsert();
      printMetaData();

      MySQLDatabase.getInstance().close();
    } catch (Exception e) {
      System.err.println("Error: " + e.getMessage());
    }
  }

  //Lab8
  static void queryStudentData() throws SQLException, Exception {
    ResultSet result = MySQLDatabase
      .getInstance()
      .executeQuery("SELECT * FROM student");

    while (result.next()) {
      System.out.println(
        result.getString("studentID") +
        " | " +
        result.getString("firstName") +
        " | " +
        result.getString("lastName") +
        " | " +
        result.getString("email")
      );
    }
  }

  //Lab9
  static void insertStudent() throws SQLException, Exception {
    String insertId = Utils.generateStudentId();
    String firstName = Utils.randomName();
    String lastName = Utils.randomName();
    String email = Utils.generateRandomEmail();

    insertId = Utils.generateStudentId();
    int rowsAffected = MySQLDatabase
      .getInstance()
      .executeUpdate(
        "INSERT INTO student (studentID, firstName, lastName, email, deptID) VALUES ('" +
        insertId +
        "', '" +
        firstName +
        "', '" +
        lastName +
        "' , '" +
        email +
        "', 'IT')"
      );

    System.out.println("Rows affected: " + rowsAffected);
  }

  //Lab10
  static void updateStudent() throws SQLException, Exception {
    String email = Utils.generateRandomEmail();

    int rowsAffected = MySQLDatabase
      .getInstance()
      .executeUpdate(
        "UPDATE student SET email = '" + email + "' WHERE studentID = '651112'"
      );

    System.out.println("Rows affected: " + rowsAffected);
  }

  //Lab11
  static void deleteStudent() throws SQLException, Exception {
    int rowsAffected = MySQLDatabase
      .getInstance()
      .executeUpdate(
        "DELETE FROM student WHERE studentID = '" + insertId + "'"
      );

    System.out.println("Rows affected: " + rowsAffected);
  }

  //Lab12
  static void preparedStatementInsert() throws SQLException, Exception {
    String studentId = Utils.generateStudentId();
    String firstName = Utils.randomName();
    String lastName = Utils.randomName();
    String email = Utils.generateRandomEmail();

    PreparedStatement preparedStatement = MySQLDatabase
      .getInstance()
      .getConnection()
      .prepareStatement(
        "INSERT INTO student (studentID, firstName, lastName, email, deptID) VALUES (?, ?, ?, ?, ?)"
      );

    preparedStatement.setString(1, studentId);
    preparedStatement.setString(2, firstName);
    preparedStatement.setString(3, lastName);
    preparedStatement.setString(4, email);
    preparedStatement.setString(5, "IT");

    int rowsAffected = preparedStatement.executeUpdate();

    System.out.println("Rows affected: " + rowsAffected);
  }

  //Lab13
  static void preparedStatementUpdate() throws SQLException, Exception {
    String email = Utils.generateRandomEmail();

    PreparedStatement preparedStatement = MySQLDatabase
      .getInstance()
      .getConnection()
      .prepareStatement("UPDATE student SET email = ? WHERE studentID = ?");

    preparedStatement.setString(1, email);
    preparedStatement.setString(2, "651112");

    int rowsAffected = preparedStatement.executeUpdate();

    System.out.println("Rows affected: " + rowsAffected);
  }

  //Lab14
  static void printMetaData() throws SQLException, Exception {
    ResultSet result = MySQLDatabase
      .getInstance()
      .executeQuery("SELECT * FROM student");

    ResultSetMetaData metaData = result.getMetaData();

    System.out.println("Total columns: " + metaData.getColumnCount());
    System.out.println(
      "Column Name of 1st column: " + metaData.getColumnName(1)
    );
    System.out.println(
      "Column Type Name of 1st column: " + metaData.getColumnTypeName(1)
    );
    System.out.println("Table Name: " + metaData.getTableName(1));

    for (int i = 1; i <= metaData.getColumnCount(); i++) System.out.printf(
      "%-14s\t",
      metaData.getColumnName(i)
    );
    System.out.println();
    while (result.next()) {
      for (int i = 1; i <= metaData.getColumnCount(); i++) System.out.printf(
        "%-14s\t",
        result.getObject(i)
      );
      System.out.println();
    }
  }
}
