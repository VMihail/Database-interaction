package org.example.experiment;

/**
 * Измеряет скорость выполнения запроса к БД
 */
public interface Experiment {
  /** Скрость закгрузки данных */
  long getUploadTime();
  /** Скорость получения данных */
  long getDataAcquisitionTime();
}
