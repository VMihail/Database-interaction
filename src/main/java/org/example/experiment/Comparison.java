package org.example.lab2.solution.experiment;

import java.sql.SQLException;

/**
 * Сравнивает скорость работы JDBC, Hibernate и myBatis
 */
public class Comparison {
  /**
   * вход в программу
   * @param args ожидается пустым
   */
  public static void main(final String ...args) {
    final int count = 100;
    try (final JdbcExperiment first = new JdbcExperiment(count)) {
      first.getUploadTime();
      final long firstResult = first.getDataAcquisitionTime();
      final HibernateExperiment second = new HibernateExperiment(count);
      final long secondResult = second.getDataAcquisitionTime();
      final MyBatisExperiment third = new MyBatisExperiment(count);
      final long thirdResult = third.getDataAcquisitionTime();
      System.out.printf("Время загрузки %d сущностей с Jdbc = %d, с hibernate = %d, c myBatis = %d%n", count, firstResult, secondResult, thirdResult);
    } catch (final SQLException e) {
      System.out.println(e.getMessage());
    }
  }
}
