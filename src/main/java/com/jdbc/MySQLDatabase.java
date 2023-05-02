package com.jdbc;

import java.sql.*;

public class MySQLDatabase {

  private static Connection connection;

  public MySQLDatabase(String url, String user, String password)
    throws SQLException {
    if (connection == null) {
      connection = DriverManager.getConnection(url, user, password);
    }
  }

  public static Connection getConnection() {
    return connection;
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
