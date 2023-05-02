package com.jdbc;

import java.sql.*;

public class MySQLDatabase {

  private Connection connection;

  public MySQLDatabase(String url, String user, String password)
    throws SQLException {
    connection = DriverManager.getConnection(url, user, password);
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
