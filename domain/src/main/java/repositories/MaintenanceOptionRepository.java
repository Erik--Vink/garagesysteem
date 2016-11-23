package repositories;

import domain.MaintenanceOption;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Kenzo Dominicus on 23-11-2016.
 */
@Stateless
public class MaintenanceOptionRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public MaintenanceOption saveOrUpdate(MaintenanceOption maintenanceOption) {
        try{
            if(maintenanceOption.getId() != 0){
                entityManager.merge(maintenanceOption);
            }
            else{
                entityManager.persist(maintenanceOption);
            }
            return maintenanceOption;
        } catch (Exception ex) {
            throw new EJBException(ex.getMessage());
        }
    }

    public MaintenanceOption getById(long id) throws Exception {
        MaintenanceOption requestedMaintenanceOption = entityManager.find(MaintenanceOption.class, id);

        if(requestedMaintenanceOption == null){
            throw new Exception("Could not find maintenanceOption with id: " + id);
        }

        return requestedMaintenanceOption;
    }

    public List<MaintenanceOption> getAll() throws Exception {
        try {
            return (List<MaintenanceOption>) entityManager.createNamedQuery("findAllMaintenanceOptions").getResultList();
        } catch (Exception ex) {
            throw new Exception("Could not get maintenanceOptions: " + ex.getMessage());
        }
    }
}
