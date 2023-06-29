package org.example.dao.hibernateDao;

import org.example.entities.Employee;
import org.example.entities.Problem;
import org.hibernate.HibernateError;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * Фабрика Hibernate Sessions. Реализует паттерн фабрика
 */
public class HibernateSessionFactoryUtil {
  /**
   * @see SessionFactory
   */
  private static SessionFactory sessionFactory;

  /**
   * Нельзя получить экземпляр класса
   */
  private HibernateSessionFactoryUtil() {}

  /**
   * Получение {@link SessionFactory}
   * @return {@link SessionFactory}
   */
  public static SessionFactory getSessionFactory() {
    if (sessionFactory == null) {
      try {
        final Configuration configuration = new Configuration().configure();
        configuration.addAnnotatedClass(Employee.class);
        configuration.addAnnotatedClass(Problem.class);
        final StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        sessionFactory = configuration.buildSessionFactory(builder.build());
      } catch (final HibernateError e) {
        System.out.println(e.getMessage());
      }
    }
    assert sessionFactory != null;
    return sessionFactory;
  }
}
