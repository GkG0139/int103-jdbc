package com.jdbc;

import io.github.cdimascio.dotenv.Dotenv;
import java.sql.*;

public class MySQLDatabase {

  private static MySQLDatabase instance = null;

  private Connection connection;

  private MySQLDatabase(String url, String username, String password)
    throws SQLException {
    connection = DriverManager.getConnection(url, username, password);
  }

  public static MySQLDatabase getInstance() throws SQLException {
    if (instance == null) {
      try {
        Dotenv dotenv = Dotenv.load();
        String dbName = dotenv.get("DB_NAME");
        String dbUser = dotenv.get("DB_USER");
        String dbPassword = dotenv.get("DB_PASSWORD");

        String url = "jdbc:mysql://localhost:3306/" + dbName;
        instance = new MySQLDatabase(url, dbUser, dbPassword);
      } catch (Exception e) {
        throw new SQLException("Error: " + e.getMessage());
      }
    }
    return instance;
  }

  public ResultSet executeQuery(String query) throws SQLException {
    Statement statement = connection.createStatement();
    ResultSet resultSet = statement.executeQuery(query);
    return resultSet;
  }

  public int executeUpdate(String query) throws SQLException {
    Statement statement = connection.createStatement();
    int rowsAffected = statement.executeUpdate(query);
    return rowsAffected;
  }

  public void close() throws SQLException {
    connection.close();
  }
}
