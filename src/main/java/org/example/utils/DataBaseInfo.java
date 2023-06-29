package org.example.utils;

public enum DataBaseInfo {
  INSTANCE;

  public final String driver = "org.postgresql.Driver";
  public final String url = "jdbc:postgresql://localhost:5432/postgres";
  public final String userName = "mihildrozdov";
  public final String pass = "QWAszx2002";
  public final String employeesTableName = "employees";
  public final String problemsTableName = "problems";
}
