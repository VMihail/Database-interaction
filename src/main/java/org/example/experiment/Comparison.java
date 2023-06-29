package org.example.experiment;

import java.sql.SQLException;

/**
 * Сравнивает скорость работы JDBC, Hibernate и myBatis
 */
public class Comparison {
  /**
   * вход в программу
   * @param args содержит одно число - количество сущностей, которое мы хотим сохранить
   */
  public static void main(final String ...args) {
    if (args.length != 1) {
      throw new IllegalArgumentException("Ожидается одно число одно число - количество сущностей, которое мы хотим сохранить");
    }
    final int count;
    try {
      count = Integer.parseInt(args[0]);
    } catch (final NumberFormatException e) {
      System.err.println("Ожидалось число");
      return;
    }
    try (final JdbcExperiment first = new JdbcExperiment(count)) {
      first.getUploadTime();
      final long firstResult = first.getDataAcquisitionTime();
      final HibernateExperiment second = new HibernateExperiment(count);
      final long secondResult = second.getDataAcquisitionTime();
      final MyBatisExperiment third = new MyBatisExperiment(count);
      final long thirdResult = third.getDataAcquisitionTime();
      System.out.printf("Время загрузки %d сущностей с Jdbc = %d, с hibernate = %d, c myBatis = %d%n",
       count, firstResult, secondResult, thirdResult);
    } catch (final SQLException e) {
      System.out.println(e.getMessage());
    }
  }
}
