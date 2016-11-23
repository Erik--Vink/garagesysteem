package repositories;

import domain.Mechanic;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by dewi on 23.11.16.
 */

@Stateless
public class MechanicRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Mechanic save(Mechanic mechanic) throws Exception {
        try {
            entityManager.persist(mechanic);
            return mechanic;
        } catch (Exception ex) {

            throw new Exception("Could not create or update " + ex);
        }
    }

    public Mechanic getById(long id) throws Exception {
        Mechanic requestMechanic = entityManager.find(Mechanic.class, id);

        if(requestMechanic == null) {
            throw new Exception("Could not find model with id " + id);
        }

        return requestMechanic;

    }

    public List<Mechanic> getAll() throws Exception {
        TypedQuery<Mechanic> query;

        try {
            query = entityManager.createNamedQuery("findAllMechanics", Mechanic.class);
            return query.getResultList();
        } catch (Exception ex) {
            throw new Exception("Could not get all " + ex);
        }

    }
}
