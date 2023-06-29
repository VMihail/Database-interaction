package org.example.experiment;

import org.example.dao.myBatisDao.MyBatisEmployeeDao;

/**
 * Релизация эксперимента для Jdbc
 */
public class MyBatisExperiment extends AbstractExperiment {
  /**
   * Создает эксперимент
   * @param numberOfRecords количество обращений к БД
   */
  public MyBatisExperiment(long numberOfRecords) {
    super(new MyBatisEmployeeDao(), numberOfRecords);
  }
}
