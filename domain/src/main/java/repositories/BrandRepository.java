package repositories;

import domain.Brand;
import interceptor.MyInterceptorBinding;

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
@MyInterceptorBinding
public class BrandRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Brand saveOrUpdate(Brand brand) {
        try{
            if(brand.getId() != 0){
                entityManager.merge(brand);
            }
            else{
                entityManager.persist(brand);
            }
            return brand;
        } catch (Exception ex) {
            throw new EJBException(ex.getMessage());
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
