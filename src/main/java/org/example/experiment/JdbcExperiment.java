package org.example.experiment;

import org.example.dao.jdbcDao.EmployeeJdbcDao;
import org.example.jdbc.JdbcConnection;
import org.example.utils.DataBaseInfo;

import java.io.Closeable;
import java.sql.SQLException;

/**
 * Релизация эксперимента для Jdbc
 */
public class JdbcExperiment implements Experiment, AutoCloseable {
  private static final DataBaseInfo info = DataBaseInfo.INSTANCE;
  private final EmployeeJdbcDao repository;
  private final long numberOfRecords;

  /**
   * Создает эксперимент
   * @param numberOfRecords количество обращений к БД
   * @throws SQLException если пройзошла ошибка на уровне БД
   */
  public JdbcExperiment(long numberOfRecords) throws SQLException {
    this.repository = new EmployeeJdbcDao(new JdbcConnection(info.url, info.userName, info.pass),
     info.employeesTableName);
    this.numberOfRecords = numberOfRecords;
  }

  /**
   * Closes this resource, relinquishing any underlying resources.
   * This method is invoked automatically on objects managed by the
   * {@code try}-with-resources statement.
   *
   * @apiNote While this interface method is declared to throw {@code
   * Exception}, implementers are <em>strongly</em> encouraged to
   * declare concrete implementations of the {@code close} method to
   * throw more specific exceptions, or to throw no exception at all
   * if the close operation cannot fail.
   *
   * <p> Cases where the close operation may fail require careful
   * attention by implementers. It is strongly advised to relinquish
   * the underlying resources and to internally <em>mark</em> the
   * resource as closed, prior to throwing the exception. The {@code
   * close} method is unlikely to be invoked more than once and so
   * this ensures that the resources are released in a timely manner.
   * Furthermore it reduces problems that could arise when the resource
   * wraps, or is wrapped, by another resource.
   *
   * <p><em>Implementers of this interface are also strongly advised
   * to not have the {@code close} method throw {@link
   * InterruptedException}.</em>
   * <p>
   * This exception interacts with a thread's interrupted status,
   * and runtime misbehavior is likely to occur if an {@code
   * InterruptedException} is {@linkplain Throwable#addSuppressed
   * suppressed}.
   * <p>
   * More generally, if it would cause problems for an
   * exception to be suppressed, the {@code AutoCloseable.close}
   * method should not throw it.
   *
   * <p>Note that unlike the {@link Closeable#close close}
   * method of {@link Closeable}, this {@code close} method
   * is <em>not</em> required to be idempotent.  In other words,
   * calling this {@code close} method more than once may have some
   * visible side effect, unlike {@code Closeable.close} which is
   * required to have no effect if called more than once.
   * <p>
   * However, implementers of this interface are strongly encouraged
   * to make their {@code close} methods idempotent.
   */
  @Override
  public void close() {
    try {
      repository.close();
    } catch (final Exception e) {
      System.out.println("Failed to close " + e.getMessage());
    }
  }

  /**
   * Скрость закгрузки данных
   */
  @Override
  public long getUploadTime() {
    return ExperimentMethods.getUploadTime(repository, numberOfRecords);
  }

  /**
   * Скорость получения данных
   */
  @Override
  public long getDataAcquisitionTime() {
    return ExperimentMethods.getDataAcquisitionTime(repository);
  }
}
