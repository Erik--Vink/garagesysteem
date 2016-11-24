package managedBeans;

import domain.Customer;
import domain.CustomerType;
import interceptor.TestInterceptor;
import repositories.CustomerRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.interceptor.Interceptors;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Erik on 24-11-2016.
 */
@Named(value = "customerController")
@Stateless
@Interceptors(TestInterceptor.class)
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

    public List<Customer> getCustomers() throws Exception {
        return customerRepository.getAll();
    }

    public List<CustomerType> getCustomerTypes(){
        return Arrays.asList(CustomerType.values());
    }

    public String save(){

        customerRepository.saveOrUpdate(this.currentCustomer);
        this.currentCustomer = new Customer();
        return "/customer/customerlist?faces-redirect=true";
    }
}
