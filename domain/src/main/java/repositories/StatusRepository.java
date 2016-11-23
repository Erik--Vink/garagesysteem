package repositories;

import domain.Status;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by dewi on 23.11.16.
 */

@Stateless
public class StatusRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Status save(Status status) throws Exception {
        try {
            entityManager.persist(status);
            return status;
        } catch (Exception ex) {

            throw new Exception("Could not create or update " + ex);
        }
    }

    public Status getById(long id) throws Exception {
        Status requestStatus = entityManager.find(Status.class, id);

        if(requestStatus== null) {
            throw new Exception("Could not find model with id " + id);
        }

        return requestStatus;

    }

    public List<Status> getAll() throws Exception {
        TypedQuery<Status> query;

        try {
            query = entityManager.createNamedQuery("findAllMechanics", Status.class);
            return query.getResultList();
        } catch (Exception ex) {
            throw new Exception("Could not get all " + ex);
        }

    }
}
