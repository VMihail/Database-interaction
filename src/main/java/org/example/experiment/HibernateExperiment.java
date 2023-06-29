package org.example.lab2.solution.experiment;

import org.example.lab2.solution.service.dao.hibernateDao.EmployeeHibernateDao;

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
