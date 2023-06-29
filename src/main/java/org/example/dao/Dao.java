package org.example.dao;

import java.util.List;

/**
 * Data Access Object. Взаимодействует с БД
 * @param <T> тип объекта в БД
 */
public interface Dao<T> {
  /**
   * Сохраняет сущность в БД
   * @param entity is not {@code null}
   * @return param
   */
  T save(T entity);

  /**
   * Удаляет сущность по ее {@code id}
   * @param id сущности в таблице
   */
  void deleteById(long id);

  /**
   * Удаляет сущность из таблицы
   * @param entity is not {@code null}
   */
  void deleteByEntity(T entity);

  /**
   * Удаляет все содерижмое таблицы
   */
  void deleteAll();

  /**
   * Обновляет сущность в таблице
   * @param entity is not {@code null}
   * @return param
   */
  T update(T entity);

  /**
   * Получить сущность по ее {@code id}
   * @param id > 0
   * @return найденная сущность
   */
  T getById(long id);

  /**
   * Получить все сущности таблицы
   * @return список всех сущностей
   */
  List<T> getAll();
}
