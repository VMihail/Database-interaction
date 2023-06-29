package org.example.dao.hibernateDao;

import org.example.dao.Dao;
import org.example.entities.Entity;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.function.Consumer;

/**
 * Реализация для Hibernate. Содержит общие детали реализации для всех сущностей
 * @param <T> тип сущности
 */
public abstract class AbstractHibernateDao<T extends Entity> implements Dao<T> {
  /**
   * Делает с сессией то, что вы скажите
   * @param consumer процедура, что вы хотите сделать с сессией
   */
  private void session(final Consumer<Session> consumer) {
    try (final Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
      consumer.accept(session);
    }
  }

  /**
   * Сохраняет сущность в БД
   * @param entity is not {@code null}
   * @return param
   */
  @Override
  public T save(final T entity) {
    session((session) -> {
      final Transaction transaction = session.beginTransaction();
      session.persist(entity);
      transaction.commit();
    });
    return entity;
  }

  /**
   * Обновляет сущность
   * @param entity is not {@code null}
   * @return param
   */
  @Override
  public T update(final T entity) {
    session((session) -> {
      final Transaction transaction = session.beginTransaction();
      session.merge(entity);
      transaction.commit();
    });
    return entity;
  }

  /**
   * Удаляет сущность
   * @param entity is not {@code null}
   */
  @Override
  public void deleteByEntity(final T entity) {
    session((session) -> {
      final Transaction transaction = session.beginTransaction();
      session.remove(entity);
      transaction.commit();
    });
  }

  /**
   * Удяляет сущность по ее {@code id}
   * @param id сущности в таблице
   */
  @Override
  public void deleteById(final long id) {
    deleteByEntity(getById(id));
  }

  /**
   * Очищает таблицу
   */
  @Override
  public void deleteAll() {
    for (final var employee : getAll()) {
      deleteByEntity(employee);
    }
  }
}
