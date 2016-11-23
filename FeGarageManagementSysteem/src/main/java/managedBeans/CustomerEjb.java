package managedBeans;

import domain.Brand;
import domain.Customer;
import domain.CustomerType;
import repositories.BrandRepository;
import repositories.CustomerRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Kenzo Dominicus on 23-11-2016.
 */
@Stateless
@Named("CustomerEjb")
public class CustomerEjb {

    private Customer customer;

    @EJB
    CustomerRepository customerRepository;

    public CustomerEjb(){
        this.customer = new Customer();
    }

    public Customer getCustomer(){
        return this.customer;
    }

    public List<CustomerType> getCustomerTypes(){
        return Arrays.asList(CustomerType.values());
    }

    public String save(Customer customer){
        try {
            customerRepository.save(customer);
            this.customer = null;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "customerlist";

    }
}
