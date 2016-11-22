package repositories;

import domain.Version;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by dewi on 22.11.16.
 */
public class VersionRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Version save(Version version) throws Exception {
        try {
            entityManager.persist(version);
            return version;
        } catch (Exception ex) {

            throw new Exception("Could not create or update " + ex);
        }
    }

    public Version getById(long id) throws Exception {
        Version requestVersion = entityManager.find(Version.class, id);

        if(requestVersion == null) {
            throw new Exception("Could not find model with id " + id);
        }

        return requestVersion;

    }

    public List<Version> getAll() throws Exception {
        TypedQuery<Version> query;

        try {
            query = entityManager.createNamedQuery("findAllModels", Version.class);
            return query.getResultList();
        } catch (Exception ex) {
            throw new Exception("Could not get all " + ex);
        }

    }
}
