package org.example.dao.jdbcDao;

import org.example.dao.Dao;
import org.example.entities.Entity;
import org.example.jdbc.JdbcConnection;

import java.sql.SQLException;
import java.util.Objects;

/**
 * Реализация для Jdbc. Содержит общие детали реализации для всех сущностей
 * @param <T> тип сущности
 */
public abstract class AbstractJdbcDao<T extends Entity> implements Dao<T>, AutoCloseable {
  /** @see JdbcConnection */
  protected final JdbcConnection source;
  /** Имя таблицы */
  protected final String tableName;

  /**
   * Инициализация объекта по {@link #source} и {@link #tableName}
   * @param source взаимодействие с БД
   * @param tableName имя таблицы
   */
  protected AbstractJdbcDao(final JdbcConnection source, final String tableName) {
    this.source = Objects.requireNonNull(source);
    this.tableName = Objects.requireNonNull(tableName);
  }

  @Override
  public void deleteAll() {
    try {
      source.executeQuery(String.format("delete from %s;", tableName));
    } catch (final SQLException e) {
      System.out.println("Failed to clear table: " + e.getMessage());
    }
  }

  /**
   * @param id сущности в таблице
   */
  @Override
  public void deleteById(final long id) {
    try {
      source.executePreparedQuery(
       String.format("delete from %s where id = ?;", tableName),
       (statement) -> {
         statement.setLong(1, id);
         statement.execute();
       }
      );
    } catch (final SQLException e) {
      System.out.println(e.getMessage());
    }
  }

  /**
   * @param entity is not {@code null}
   */
  @Override
  public void deleteByEntity(final T entity) {
    deleteById(entity.getId());
  }

  /**
   * Закрывает Dao
   */
  @Override
  public void close() {
    try {
      source.close();
    } catch (final SQLException e) {
      System.out.println("Failed to close dao " + e.getMessage());
    }
  }
}
