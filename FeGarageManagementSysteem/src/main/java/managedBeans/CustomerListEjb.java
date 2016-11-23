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
@Named("CustomerListEjb")
public class CustomerListEjb {

    @EJB
    CustomerRepository customerRepository;

    public List<Customer> getCustomers(){
        try {
            return customerRepository.getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String goToDetail(Customer customer){
        System.out.println(customer);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("customer", customer);
        return "customerdetails?faces-redirect=true";
    }
}
