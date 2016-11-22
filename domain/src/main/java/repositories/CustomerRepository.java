package repositories;

import domain.Customer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Erik on 22-11-2016.
 */
public class CustomerRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Customer create(String name){
        Customer customer = new Customer();
        customer.setFirstName(name);
        entityManager.persist(customer);
        return customer;
    }

}
