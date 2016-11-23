package repositories;

import domain.Maintenance;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Kenzo Dominicus on 23-11-2016.
 */
@Stateless
public class MaintenanceRepository {

    @PersistenceContext
    EntityManager entityManager;

    public Maintenance save(Maintenance maintenance){
        try {
            entityManager.persist(maintenance);
            entityManager.flush();
            return maintenance;
        } catch (Exception ex) {
            throw new EJBException(ex.getMessage());
        }
    }

    public Maintenance getById(long id) throws Exception {
        Maintenance requestedMaintenance = entityManager.find(Maintenance.class, id);

        if(requestedMaintenance == null){
            throw new Exception("Could not find maintenances with id: " + id);
        }

        return requestedMaintenance;
    }

    public List<Maintenance> getAll() throws Exception {
        try {
            return (List<Maintenance>) entityManager.createNamedQuery("findAllMaintenances").getResultList();
        } catch (Exception ex) {
            throw new Exception("Could not get maintenances: " + ex.getMessage());
        }
    }
}
