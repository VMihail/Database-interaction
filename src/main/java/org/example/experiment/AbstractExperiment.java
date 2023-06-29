package org.example.lab2.solution.experiment;

import org.example.lab2.solution.entities.Employee;
import org.example.lab2.solution.service.dao.Dao;

import java.util.Date;

/**
 * Общие детали реализации для всех экспериментов
 */
public class AbstractExperiment implements Experiment {
  /** @see Dao */
  protected final Dao<Employee> repository;

  /** Количество сущностей, с которыми мы взаимодействуем */
  protected final long numberOfRecords;

  /**
   * Стандартный конструктор
   * @param repository {@link #repository}
   * @param numberOfRecords {@link #numberOfRecords}
   */
  public AbstractExperiment(Dao<Employee> repository, long numberOfRecords) {
    this.repository = repository;
    this.numberOfRecords = numberOfRecords;
  }

  /**
   * Скрость закгрузки данных
   */
  @Override
  public long getUploadTime() {
    clear();
    long startTime = System.currentTimeMillis();
    for (long i = 1; i <= numberOfRecords; i++) {
      repository.save(new Employee(i, "Test" + i, new Date()));
    }
    return System.currentTimeMillis() - startTime;
  }

  /**
   * Скорость получения данных
   */
  @Override
  public long getDataAcquisitionTime() {
    long startTime = System.currentTimeMillis();
    repository.getAll();
    return System.currentTimeMillis() - startTime;
  }

  /** Очищает таблицу */
  public void clear() {
    repository.deleteAll();
  }
}
