package repositories;

import domain.Customer;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
public class ConfigBean {

    @EJB
    private CustomerRepository customerRepository;

    @PostConstruct
    public void createData() {
        Customer customer1 = customerRepository.save(Customer.builder().firstName("Tom").lastName("Cool").build());
    }
}
