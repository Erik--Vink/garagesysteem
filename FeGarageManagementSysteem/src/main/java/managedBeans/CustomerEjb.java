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
        Customer customer = (Customer) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("customer");
        if(customer == null){
            this.customer = new Customer();
        }
        else{
            this.customer = customer;
        }
        System.out.println(customer);
    }

    public Customer getCustomer(){
        return this.customer;
    }

    public List<CustomerType> getCustomerTypes(){
        return Arrays.asList(CustomerType.values());
    }

    public String save(){
        try {
            customerRepository.save(this.customer);
            this.customer = new Customer();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/index?faces-redirect=true";
    }
}
