package repositories;

import domain.CustomerCar;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Kenzo Dominicus on 23-11-2016.
 */
@Stateless
public class CustomerCarRepository {

    @PersistenceContext
    EntityManager entityManager;

    public CustomerCar save(CustomerCar customerCar){
        try{
            entityManager.persist(customerCar);
            entityManager.flush();
            return customerCar;
        } catch (Exception ex){
            throw new EJBException(ex.getMessage());
        }
    }

    public CustomerCar getById(long id) throws Exception{
        CustomerCar requestedCustomerCar = entityManager.find(CustomerCar.class, id);
        if(requestedCustomerCar == null){
            throw new Exception("Could not find customer with id: " + id);
        }
        return requestedCustomerCar;
    }

    public List<CustomerCar> getAll() throws Exception{
        try {
            return (List<CustomerCar>) entityManager.createNamedQuery("findAllCustomerCars").getResultList();
        } catch (Exception ex) {
            throw new Exception("Could not get customerCars: " + ex.getMessage());
        }
    }
}
