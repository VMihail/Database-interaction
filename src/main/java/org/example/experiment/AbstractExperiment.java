package org.example.experiment;

import org.example.dao.Dao;
import org.example.entities.Employee;

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
    return ExperimentMethods.getUploadTime(repository, numberOfRecords);
  }

  /**
   * Скорость получения данных
   */
  @Override
  public long getDataAcquisitionTime() {
    return ExperimentMethods.getDataAcquisitionTime(repository);
  }
}
