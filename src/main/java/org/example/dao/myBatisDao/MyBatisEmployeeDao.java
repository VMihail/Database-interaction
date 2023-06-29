package org.example.dao.myBatisDao;

import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.example.dao.Dao;
import org.example.dao.myBatisDao.mappers.EmployeeMapper;
import org.example.entities.Employee;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Реализация {@link Dao<Employee>} через MyBatis
 */
public class MyBatisEmployeeDao extends AbstractMyBatisDao implements Dao<Employee> {
  /**
   * Выполняет процедуру с {@link #sessionFactory}
   * @param consumer процедура
   */
  private void executeEmployeeConsumer(final Consumer<EmployeeMapper> consumer) {
    try (final SqlSession session = sessionFactory.openSession()) {
      final EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
      consumer.accept(mapper);
    }
  }

  /**
   * Выполняет функцию с {@link #sessionFactory}
   * @param function функция
   * @return результат функции
   * @param <T> тип результата
   */
  private <T> T executeEmployeeFunction(final Function<EmployeeMapper, T> function) {
    try (final SqlSession session = sessionFactory.openSession()) {
      final EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
      return function.apply(mapper);
    }
  }

  @Override
  protected void initImpl(final Configuration configuration) {
    configuration.addMapper(EmployeeMapper.class);
  }

  @Override
  public Employee save(Employee entity) {
    return executeEmployeeFunction(employeeMapper -> employeeMapper.save(entity));
  }

  @Override
  public void deleteById(long id) {
    executeEmployeeConsumer(employeeMapper -> employeeMapper.deleteById(id));
  }

  @Override
  public void deleteByEntity(Employee entity) {
    deleteById(entity.getId());
  }

  @Override
  public void deleteAll() {
    executeEmployeeConsumer(EmployeeMapper::deleteAll);
  }

  @Override
  public Employee update(Employee entity) {
    executeEmployeeConsumer(employeeMapper ->
     employeeMapper.update(entity.getId(), entity.getName(), entity.getDateOfBirthday()));
    return entity;
  }

  @Override
  public Employee getById(long id) {
    return executeEmployeeFunction(mapper -> mapper.getById(id));
  }

  @Override
  public List<Employee> getAll() {
    return executeEmployeeFunction(EmployeeMapper::getAll);
  }
}
