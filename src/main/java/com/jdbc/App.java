package com.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

public class App {

  public static void main(String[] args) {
    try {
      queryStudentData();

      MySQLDatabase.getInstance().close();
    } catch (Exception e) {
      System.err.println("Error: " + e.getMessage());
    }
  }

  static void queryStudentData() throws SQLException, Exception {
    ResultSet result = MySQLDatabase
      .getInstance()
      .executeQuery("SELECT * FROM student");

    while (result.next()) {
      System.out.println(
        result.getString("studentID") +
        " " +
        result.getString("firstName") +
        " " +
        result.getString("lastName")
      );
    }
  }
}
