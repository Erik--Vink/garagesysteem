package repositories;

import domain.Customer;
import interceptor.ErrorLoggingInterceptorBinding;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Erik on 22-11-2016.
 */
@Stateless
@ErrorLoggingInterceptorBinding
public class CustomerRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Customer saveOrUpdate(Customer customer) {
        try{
            if(customer.getId() != 0){
                entityManager.merge(customer);
            }
            else{
                entityManager.persist(customer);
            }
            return customer;
        } catch (Exception ex) {
            throw new EJBException(ex.getMessage());
        }
    }

    public Customer getById(long id) throws Exception {
        Customer requestedCustomer = entityManager.find(Customer.class, id);

        if(requestedCustomer == null){
            throw new Exception("Could not find customer with id: " + id);
        }

        return requestedCustomer;
    }

    public List<Customer> getAll() throws Exception {
        try {
            return (List<Customer>) entityManager.createNamedQuery("findAllCustomers").getResultList();
        } catch (Exception ex) {
            throw new Exception("Could not get customers: " + ex.getMessage());
        }
    }
}
