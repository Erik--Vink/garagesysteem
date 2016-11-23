package managedBeans;

import domain.Customer;
import domain.CustomerType;
import repositories.CustomerRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
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
        customer = new Customer();
    }

    public void loadExistingCustomer(){
        if(this.customer.getId() != 0){
            try {
                this.customer = customerRepository.getById(this.customer.getId());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Customer getCustomer(){
        return this.customer;
    }

    public List<CustomerType> getCustomerTypes(){
        return Arrays.asList(CustomerType.values());
    }

    public String save(){
        try {
            System.out.println(this.customer);
            customerRepository.save(this.customer);
            this.customer = new Customer();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/index?faces-redirect=true";
    }
}
