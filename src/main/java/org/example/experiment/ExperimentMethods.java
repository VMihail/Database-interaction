package org.example.experiment;

import org.example.dao.Dao;
import org.example.entities.Employee;

import java.sql.Date;

public final class AbstractExperimentMethods {
  public static long getUploadTime(final Dao<Employee> repository, final long numberOfRecords) {
    clear(repository);
    final long startTime = System.currentTimeMillis();
    for (long i = 1; i <= numberOfRecords; i++) {
      repository.save(new Employee(i, "Test" + i, new Date(new java.util.Date().getTime())));
    }
    return System.currentTimeMillis() - startTime;
  }

  public static long getDataAcquisitionTime(final Dao<Employee> repository) {
    final long startTime = System.currentTimeMillis();
    repository.getAll();
    return System.currentTimeMillis() - startTime;
  }

  /** Очищает таблицу */
  public static void clear(final Dao<Employee> repository) {
    repository.deleteAll();
  }
}
