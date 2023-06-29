package org.example.dao.myBatisDao;

import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.example.dao.Dao;
import org.example.dao.myBatisDao.mappers.ProblemMapper;
import org.example.entities.Problem;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class MyBatisProblemDao extends AbstractMyBatisDao implements Dao<Problem> {
  private void executeEmployeeConsumer(final Consumer<ProblemMapper> consumer) {
    try (final SqlSession session = sessionFactory.openSession()) {
      final ProblemMapper mapper = session.getMapper(ProblemMapper.class);
      consumer.accept(mapper);
    }
  }

  private <T> T executeEmployeeFunction(final Function<ProblemMapper, T> function) {
    try (final SqlSession session = sessionFactory.openSession()) {
      final ProblemMapper mapper = session.getMapper(ProblemMapper.class);
      return function.apply(mapper);
    }
  }

  @Override
  protected void initImpl(final Configuration configuration) {
    configuration.addMapper(ProblemMapper.class);
  }

  @Override
  public Problem getById(long id) {
    return executeEmployeeFunction(problemMapper -> problemMapper.getById(id));
  }

  @Override
  public List<Problem> getAll() {
    return executeEmployeeFunction(ProblemMapper::getAll);
  }

  @Override
  public void deleteAll() {
    executeEmployeeConsumer(ProblemMapper::deleteAll);
  }

  @Override
  public Problem update(Problem entity) {
    return executeEmployeeFunction(problemMapper -> problemMapper.update(entity));
  }

  @Override
  public Problem save(Problem entity) {
    return executeEmployeeFunction(problemMapper -> problemMapper.save(entity));
  }

  @Override
  public void deleteById(long id) {
    executeEmployeeConsumer(problemMapper -> problemMapper.deleteById(id));
  }

  @Override
  public void deleteByEntity(Problem entity) {
    deleteById(entity.getId());
  }
}
