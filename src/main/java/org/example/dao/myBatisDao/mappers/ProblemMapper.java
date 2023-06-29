package org.example.dao.myBatisDao.mappers;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.example.dao.Dao;
import org.example.entities.Problem;

import java.util.Date;
import java.util.List;

/**
 * Содержит sql запросы для взаимодействия с таблицей Problems.
 * @see Problem
 */
public interface ProblemMapper extends Dao<Problem> {
  /**
   * Получить {@link Problem} по его {@code id}
   * @param id > 0
   * @return {@link Problem} : problem.id == param.id
   */
  @Select("select * from Problems where id=#{id};")
  @Results(value = {
   @Result(property = "id", column = "id"),
   @Result(property = "name", column = "name"),
   @Result(property = "deadline", column = "deadline"),
   @Result(property = "description", column = "description"),
   @Result(property = "type", column = "type"),
   @Result(property = "employeeid", column = "employeeid")
  })
  Problem getById(long id);

  /**
   * Получить список всех {@link Problem}
   * @return {@link List<Problem>} содержащий все задачи таблицы
   */
  @Select("select * from Problems;")
  List<Problem> getAll();

  /**
   * Удаляет всех {@link Problem} из таблицы
   */
  @Delete("delete from Problems;")
  void deleteAll();

  /**
   * Удалить {@link Problem} по его {@code id}
   * @param id сущности в таблице
   */
  @Delete("delete from Problems where id=#{id};")
  void deleteById(long id);

  /**
   * Сохранить {@link Problem} в таблицу
   * @param problem is not {@code null}
   * @return param
   */
  @Insert("insert into Problems(id, name, deadline, description, type, personId) values (#{id}, #{name}, #{deadline}, #{description}, #{type}, #{personId});")
  Problem save(Problem problem);

  /**
   * Обновляет {@link Problem}
   * @param id {@code id} задачи
   * @param name имя задачи
   * @param deadline дедлайн задачи
   * @param description описание задачи
   * @param type тип задачи
   * @param personId {@code id} задачи
   */
  @Update("update Problems set id = #{id}, name = #{name}, deadline = #{deadline}, description = #{description}, type = #{type}, employeeid = #{employeeid} where id = #{id};")
  void update(long id, String name, Date deadline, String description, String type, long personId);
}
