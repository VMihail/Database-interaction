package org.example.dao.jdbcDao;

import org.example.entities.Employee;
import org.example.jdbc.JdbcConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Реализация для {@link Employee}
 */
public class EmployeeJdbcDao extends AbstractJdbcDao<Employee> {
  /**
   * Инициализирует Dao для соотрудника
   */
  public EmployeeJdbcDao(final JdbcConnection source, final String tableName) {
    super(source, tableName);
  }

  /**
   * Сохраняет соотрудника
   * @param entity is not {@code null}
   * @return param
   */
  @Override
  public Employee save(final Employee entity) {
    try {
      source.executePreparedQuery(
       String.format("insert into %s (id, name, DOB) values (?, ?, ?);", tableName),
       (statement) -> {
         statement.setLong(1, entity.getId());
         statement.setString(2, entity.getName());
         statement.setDate(3, entity.getDateOfBirthday());
         statement.execute();
       });
    } catch (final SQLException e) {
      System.out.println("Failed to save user " + e.getMessage());
    }
    return entity;
  }

  /**
   * Обновить соотрудника
   * @param entity is not {@code null}
   * @return param
   */
  @Override
  public Employee update(Employee entity) {
    try {
      source.executePreparedQuery(
       String.format("update %s set id = ?, name = ?, DOB = ? where id = ?;", tableName),
       (statement) -> {
         statement.setLong(1, entity.getId());
         statement.setString(2, entity.getName());
         statement.setDate(3, entity.getDateOfBirthday());
         statement.setLong(4, entity.getId());
         statement.execute();
       }
      );
    } catch (final SQLException e) {
      System.out.println(e.getMessage());
    }
    return entity;
  }

  /**
   * Получить соотрудника по его id
   * @param id > 0
   * @return соотрудник с этим {@code id}
   */
  @Override
  public Employee getById(long id) {
    try {
      return source.executePreparedQuery(
       String.format("select * from %s where id = ?;", tableName),
       (statement) -> statement.setLong(1, id),
       (resultSet) -> new Employee(
        resultSet.getLong(1),
        resultSet.getString(2),
        resultSet.getDate(3)
       )
      );
    } catch (final SQLException e) {
      System.out.println(String.format("Failed to get a user by his number: %d", id) + e.getMessage());
    }
    throw new AssertionError();
  }

  /**
   * Получить всех соотрудников
   * @return список всех соотрудников
   */
  @Override
  public List<Employee> getAll() {
    final List<Employee> result = new ArrayList<>();
    try {
      source.executeQuery(
       String.format("select * from %s;", tableName),
       (resultSet) -> {
         while (resultSet.next()) {
           result.add(getEmployee(resultSet));
         }
       }
      );
    } catch (final SQLException e) {
      System.out.println();
    }
    return result;
  }

  /**
   * Получение {@link Employee} из {@link ResultSet}
   * @param resultSet непустой
   * @return {@link Employee}
   * @throws SQLException если произошла ошибка уроня БД
   */
  private Employee getEmployee(final ResultSet resultSet) throws SQLException {
    Objects.requireNonNull(resultSet);
    return new Employee(resultSet.getLong(1),
     resultSet.getString(2),
     resultSet.getDate(3));
  }
}
