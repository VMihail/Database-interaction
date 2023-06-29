package org.example.experiment;

import org.example.dao.hibernateDao.EmployeeHibernateDao;

/**
 * Релизация эксперимента для Hibernate
 */
public class HibernateExperiment extends AbstractExperiment {
  /**
   * Создает эксперимент
   * @param numberOfRecords количество обращений к БД
   */
  public HibernateExperiment(long numberOfRecords) {
    super(new EmployeeHibernateDao(), numberOfRecords);
  }
}
