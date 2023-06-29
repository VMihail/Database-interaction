package org.example.dao.jdbcDao;

import org.example.entities.Problem;
import org.example.jdbc.JdbcConnection;
import org.example.utils.ObjectsUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Реализация для {@link Problem}
 */
public class ProblemJdbcDao extends AbstractJdbcDao<Problem> {
  /**
   * Инициализирует Dao для задачи
   */
  public ProblemJdbcDao(final JdbcConnection source, final String tableName) {
    super(source, tableName);
  }

  /**
   * Сохраняет задачу
   * @param entity is not {@code null}
   * @return param
   */
  @Override
  public Problem save(Problem entity) {
    try {
      source.executePreparedQuery(
       String.
        format("insert into %s (id, name, deadline, description, type, personId) values (?, ?, ?, ?, ?, ?);", tableName),
       (statement) -> {
         setProblemInfo(entity, statement);
         statement.execute();
       });
    } catch (final SQLException e) {
      System.out.println("Failed to save user " + e.getMessage());
    }
    return entity;
  }

  @Override
  public void deleteAll() {
    try {
      source.executeQuery(String.format("delete from %s;", tableName));
    } catch (final SQLException e) {
      System.out.println(e.getMessage());
    }
  }

  /**
   * Обновить задачу
   * @param entity is not {@code null}
   * @return param
   */
  @Override
  public Problem update(Problem entity) {
    try {
      source.executePreparedQuery(
       String.
        format("update %s set id = ?, name = ?, deadline = ?, description = ?, type = ?, personId = ? where id = ?;", tableName),
       (statement) -> {
         setProblemInfo(entity, statement);
         statement.setLong(7, entity.getId());
         statement.execute();
       }
      );
    } catch (final SQLException e) {
      System.out.println(e.getMessage());
    }
    return entity;
  }

  /**
   * Получить задачу по его id
   * @param id > 0
   * @return соотрудник с этим {@code id}
   */
  @Override
  public Problem getById(long id) {
    try {
      return source.executePreparedQuery(
       String.format("select * from %s where id = ?;", tableName),
       (statement) -> statement.setLong(1, id),
       this::getProblemInfo
      );
    } catch (final SQLException e) {
      System.out.println(String.format("Failed to get a user by his number: %d", id) + e.getMessage());
    }
    throw new AssertionError();
  }

  /**
   * Получить все задачи
   * @return список всех соотрудников
   */
  @Override
  public List<Problem> getAll() {
    final List<Problem> result = new ArrayList<>();
    try {
      source.executeQuery(
       String.format("select * from %s;", tableName),
       (resultSet) -> {
         while (resultSet.next()) {
           result.add(getProblemInfo(resultSet));
         }
       }
      );
    } catch (final SQLException e) {
      System.out.println(e.getMessage());
    }
    return result;
  }

  /**
   * Установить {@link Problem} в {@link PreparedStatement}
   * @param entity сущность, которую мы хотим установить
   * @param statement куда хотим установить сущность
   * @throws SQLException если произошла ошибка на уровне БД
   */
  private void setProblemInfo(Problem entity, PreparedStatement statement) throws SQLException {
    ObjectsUtils.throwNPEIfNull(entity, statement);
    statement.setLong(1, entity.getId());
    statement.setString(2, entity.getName());
    statement.setDate(3, entity.getDeadline());
    statement.setString(4, entity.getDescription());
    statement.setString(5, entity.getType());
    statement.setLong(6, entity.getEmployeeId());
  }

  /**
   * Получить {@link Problem} из {@link ResultSet}
   * @param resultSet непустой
   * @return {@link Problem}
   * @throws SQLException если произошла ошибка на уровне БД
   */
  private Problem getProblemInfo(final ResultSet resultSet) throws SQLException {
    Objects.requireNonNull(resultSet);
    return new Problem(
     resultSet.getLong(1),
     resultSet.getString(2),
     resultSet.getDate(3),
     resultSet.getString(4),
     resultSet.getString(5),
     resultSet.getLong(6)
    );
  }
}
