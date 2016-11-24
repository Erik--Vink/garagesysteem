package repositories;

import domain.DbLogger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by dewi on 24.11.16.
 */
@Stateless
public class DbLoggerRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public DbLogger logException(DbLogger dbLogger) throws Exception {
        try {
            entityManager.persist(dbLogger);
            return dbLogger;
        } catch (Exception ex) {
            throw new Exception();
        }
    }
}
