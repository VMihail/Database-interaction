package org.example.dao.hibernateDao;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.example.entities.Employee;
import org.hibernate.Session;

import java.util.List;

/**
 * Реализация для {@link Employee}
 */
public class EmployeeHibernateDao extends AbstractHibernateDao<Employee> {
  /**
   * Получить соотрудника по его {@code id}
   * @param id > 0
   * @return соотдрудник с id == param
   */
  @Override
  public Employee getById(final long id) {
    try (final Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
      return session.get(Employee.class, id);
    }
  }

  /**
   * Получить всех соотрудников
   * @return список всех соотрудиников
   */
  @Override
  public List<Employee> getAll() {
    try (final Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
      final CriteriaBuilder cb = session.getCriteriaBuilder();
      final CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
      final Root<Employee> rootEntry = cq.from(Employee.class);
      final CriteriaQuery<Employee> all = cq.select(rootEntry);
      final TypedQuery<Employee> allQuery = session.createQuery(all);
      return allQuery.getResultList();
    }
  }
}
