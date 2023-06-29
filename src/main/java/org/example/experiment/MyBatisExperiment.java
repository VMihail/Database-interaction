package org.example.lab2.solution.experiment;

import org.example.lab2.solution.DbInfo;
import org.example.lab2.solution.service.dao.myBatisDao.MyBatisEmployeeDao;

/**
 * Релизация эксперимента для Jdbc
 */
public class MyBatisExperiment extends AbstractExperiment {
  /**
   * Создает эксперимент
   * @param numberOfRecords количество обращений к БД
   */
  public MyBatisExperiment(long numberOfRecords) {
    super(new MyBatisEmployeeDao(DbInfo.driver, DbInfo.url), numberOfRecords);
  }
}
