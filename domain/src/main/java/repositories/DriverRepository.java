package repositories;

import domain.Driver;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Kenzo Dominicus on 23-11-2016.
 */
@Stateless
public class DriverRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public Driver save(Driver driver){
        try{
            entityManager.persist(driver);
            entityManager.flush();
            return driver;
        } catch (Exception ex) {
            throw new EJBException(ex.getMessage());
        }
    }

    public Driver getById(long id) throws Exception {
        Driver requestedDriver = entityManager.find(Driver.class, id);

        if(requestedDriver == null){
            throw new Exception("Could not find driver with id: " + id);
        }

        return requestedDriver;
    }

    public List<Driver> getAll() throws Exception {
        try {
            return (List<Driver>) entityManager.createNamedQuery("findAllDrivers").getResultList();
        } catch (Exception ex) {
            throw new Exception("Could not get drivers: " + ex.getMessage());
        }
    }
}
