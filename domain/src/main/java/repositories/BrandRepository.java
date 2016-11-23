package repositories;

import domain.Brand;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by dewi on 22.11.16.
 */
@Stateless
public class BrandRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Brand save(Brand brand) throws Exception {
        try {
            entityManager.persist(brand);
            return brand;
        } catch (Exception ex) {

            throw new Exception("Could not create or update " + ex);
        }
    }

    public Brand getById(long id) throws Exception {
        Brand requestBrand = entityManager.find(Brand.class, id);

        if(requestBrand == null) {
            throw new Exception("Could not find model with id " + id);
        }

        return requestBrand;

    }

    public List<Brand> getAll() throws Exception {
        TypedQuery<Brand> query;

        try {
            query = entityManager.createNamedQuery("findAllBrands", Brand.class);
            return query.getResultList();
        } catch (Exception ex) {
            throw new Exception("Could not get all " + ex);
        }

    }

}
