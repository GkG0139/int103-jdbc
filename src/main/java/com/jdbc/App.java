package com.jdbc;

import io.github.cdimascio.dotenv.Dotenv;
import java.sql.ResultSet;

public class App {

  public static void main(String[] args) {
    try {
      Dotenv dotenv = Dotenv.load();
      String dbName = dotenv.get("DB_NAME");
      String dbUser = dotenv.get("DB_USER");
      String dbPassword = dotenv.get("DB_PASSWORD");

      String url = "jdbc:mysql://localhost:3306/" + dbName;
      MySQLDatabase db = new MySQLDatabase(url, dbUser, dbPassword);

      ResultSet result = db.executeQuery("SELECT * FROM student");

      while (result.next()) {
        System.out.println(
          result.getString("studentID") +
          " " +
          result.getString("firstName") +
          " " +
          result.getString("lastName")
        );
      }

      db.close();
    } catch (Exception e) {
      System.err.println("Error: " + e.getMessage());
    }
  }
}
