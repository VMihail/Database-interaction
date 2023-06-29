package org.example.dao.hibernateDao;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.example.entities.Problem;
import org.hibernate.Session;

import java.util.List;

/**
 * Реализация для {@link Problem}
 */
public class ProblemHibernateDao extends AbstractHibernateDao<Problem> {
  /**
   * Получить задачу по ее {@code id}
   * @param id > 0
   * @return задача с id == param
   */
  @Override
  public Problem getById(final long id) {
    try (final Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
      return session.get(Problem.class, id);
    }
  }

  /**
   * Полчить все задачи
   * @return список всез задач
   */
  @Override
  public List<Problem> getAll() {
    try (final Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
      final CriteriaBuilder cb = session.getCriteriaBuilder();
      final CriteriaQuery<Problem> cq = cb.createQuery(Problem.class);
      final Root<Problem> rootEntry = cq.from(Problem.class);
      final CriteriaQuery<Problem> all = cq.select(rootEntry);
      final TypedQuery<Problem> allQuery = session.createQuery(all);
      return allQuery.getResultList();
    }
  }
}
