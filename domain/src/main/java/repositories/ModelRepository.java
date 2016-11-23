package repositories;

import domain.Model;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by dewi on 22.11.16.
 */
@Stateless
public class ModelRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Model save(Model model) throws Exception {
        try {
            entityManager.persist(model);
            return model;
        } catch (Exception ex) {

            throw new Exception("Could not create or update " + ex);
        }
    }

    public Model getById(long id) throws Exception {
        Model requestModel = entityManager.find(Model.class, id);

        if(requestModel == null) {
            throw new Exception("Could not find model with id " + id);
        }

        return requestModel;

    }

    public List<Model> getAll() throws Exception {
        TypedQuery<Model> query;

        try {
            query = entityManager.createNamedQuery("findAllModels", Model.class);
            return query.getResultList();
        } catch (Exception ex) {
            throw new Exception("Could not get all " + ex);
        }

    }


}
