package managedBeans;

import domain.Customer;
import domain.CustomerType;
import repositories.CustomerRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Erik on 24-11-2016.
 */
@Named(value = "customerController")
@Stateless
public class CustomerController {

    private Customer currentCustomer;

    @EJB
    CustomerRepository customerRepository;

    public CustomerController(){}

    public String prepareCreate(){
        this.currentCustomer = new Customer();
        return "/customer/customerdetails?faces-redirect=true";
    }

    public String prepareEdit(Customer customer){
        this.currentCustomer = customer;
        return "/customer/customerdetails?faces-redirect=true";
    }

    public String prepareList(){
        return "/customer/customerlist?faces-redirect=true";
    }

    public Customer getCustomer(){
        return this.currentCustomer;
    }

    public List<Customer> getCustomers(){
        try {
            return customerRepository.getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<CustomerType> getCustomerTypes(){
        return Arrays.asList(CustomerType.values());
    }

    public String save(){
        try {
            customerRepository.saveOrUpdate(this.currentCustomer);
            this.currentCustomer = new Customer();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/index?faces-redirect=true";
    }
}
