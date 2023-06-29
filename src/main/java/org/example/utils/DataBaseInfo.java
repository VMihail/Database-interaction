package org.example.utils;

public enum DataBaseInfo {
  INSTANCE;

  public final String driver = "org.postgresql.Driver";
  public final String url = "jdbc:postgresql://localhost:5432/postgres";
  public final String userName = "name";
  public final String pass = "pass";
  public final String employeesTableName = "employees";
  public final String problemsTableName = "problems";
}
