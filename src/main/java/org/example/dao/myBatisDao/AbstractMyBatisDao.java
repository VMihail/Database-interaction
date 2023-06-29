package org.example.dao.myBatisDao;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.example.utils.DataBaseInfo;

import javax.sql.DataSource;

/**
 * Общие детали реализации {@link MyBatisEmployeeDao} и {@link MyBatisProblemDao}
 */
public abstract class AbstractMyBatisDao {
  protected SqlSessionFactory sessionFactory;

  public AbstractMyBatisDao() {
    init();
  }

  /**
   * Создает {@link #sessionFactory} для маппера
   */
  protected abstract void initImpl(final Configuration configuration);

  public void init() {
    DataBaseInfo info = DataBaseInfo.INSTANCE;
    final DataSource dataSource = new PooledDataSource(info.driver, info.url, info.userName, info.pass);
    final Environment environment = new Environment("Main", new JdbcTransactionFactory(), dataSource);
    final Configuration configuration = new Configuration(environment);
    initImpl(configuration);
    final SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
    sessionFactory = builder.build(configuration);
  }
}