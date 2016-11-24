package repositories;

import domain.Version;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by dewi on 22.11.16.
 */
@Stateless
public class VersionRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Version saveOrUpdate(Version version) {
        try{
            if(version.getId() != 0){
                entityManager.merge(version);
            }
            else{
                entityManager.persist(version);
            }
            return version;
        } catch (Exception ex) {
            throw new EJBException(ex.getMessage());
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
            query = entityManager.createNamedQuery("findAllVersions", Version.class);
            return query.getResultList();
        } catch (Exception ex) {
            throw new Exception("Could not get all " + ex);
        }

    }
}
