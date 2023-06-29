package org.example.dao.myBatisDao.mappers;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.example.dao.Dao;
import org.example.entities.Employee;

import java.util.Date;
import java.util.List;

/**
 * Содержит sql запросы для взаимодействия с таблицей Persons.
 * @see Employee
 */
public interface EmployeeMapper extends Dao<Employee> {
  /**
   * Получить {@link Employee} по его {@code id}
   * @param id > 0
   * @return {@link Employee} : employee.id == param.id
   */
  @Select("select * from employees where id=#{id};")
  @Results (value = {
   @Result (property = "id", column = "id"),
   @Result(property = "name", column = "name"),
   @Result(property = "DOB", column = "DOB")
  })
  Employee getById(long id);

  /**
   * Получить список всех {@link Employee}
   * @return {@link List<Employee>} содержащий всех соотрудников таблицы
   */
  @Select("select * from employees;")
  List<Employee> getAll();

  /**
   * Удаляет всех {@link Employee} из таблицы
   */
  @Delete("delete from employees;")
  void deleteAll();

  /**
   * Удалить {@link Employee} по его {@code id}
   * @param id сущности в таблице
   */
  @Delete("delete from employees where id=#{id};")
  void deleteById(long id);

  /**
   * Сохранить {@link Employee} в таблицу
   * @param employee is not {@code null}
   * @return param
   */
  @Insert("insert into employees(id, name, DOB) values (#{id}, #{name}, #{DOB});")
  Employee save(Employee employee);

  /**
   * Обновляет {@link Employee}
   * @param id id соотрудника
   * @param name имя соотрудника
   * @param Dob дата рождения соотрудника
   */
  @Update("update employees set id = #{id}, name = #{name}, DOB = #{Dob} where id = #{id};")
  void update(long id, String name, Date Dob);
}
