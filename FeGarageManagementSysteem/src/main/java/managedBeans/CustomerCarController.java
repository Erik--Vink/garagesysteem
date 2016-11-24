package managedBeans;

import domain.Customer;
import domain.CustomerCar;
import domain.Driver;
import domain.Version;
import interceptor.TestInterceptor;
import repositories.CustomerCarRepository;
import repositories.CustomerRepository;
import repositories.DriverRepository;
import repositories.VersionRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.interceptor.Interceptors;
import java.util.List;

/**
 * Created by Kenzo Dominicus on 24-11-2016.
 */
@Stateless
@Named("customerCarController")
@Interceptors(TestInterceptor.class)
public class CustomerCarController {
    private long driverId, customerId, versionId;
    private CustomerCar customerCar;

    @EJB
    private VersionRepository versionRepository;
    @EJB
    private CustomerRepository customerRepository;
    @EJB
    private DriverRepository driverRepository;
    @EJB
    private CustomerCarRepository customerCarRepository;

    public CustomerCarController() {
        this.customerCar = new CustomerCar();
        this.driverId = 0;
        this.customerId = 0;
        this.versionId = 0;
    }

    public long getDriverId() {
        return driverId;
    }

    public void setDriverId(long driverId) {
        this.driverId = driverId;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public long getVersionId() {
        return versionId;
    }

    public void setVersionId(long versionId) {
        this.versionId = versionId;
    }

    public CustomerCar getCustomerCar() {
        return customerCar;
    }

    public void setCustomerCar(CustomerCar customerCar) {
        this.customerCar = customerCar;
    }

    public List<Version> getVersions() {
        List<Version> versions = null;
        try {
            versions = versionRepository.getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return versions;
    }

    public List<Customer> getCustomers() {
        List<Customer> customers = null;
        try {
            customers = customerRepository.getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customers;
    }

    public List<Driver> getDrivers() {
        List<Driver> drivers = null;
        try {
            drivers = driverRepository.getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return drivers;
    }

    public String save() {
        try {
            customerCar.setVersion(versionRepository.getById(versionId));
            customerCar.setCustomer(customerRepository.getById(customerId));
            if(driverId != 0){
                customerCar.setDriver(driverRepository.getById(driverId));
            }


            customerCarRepository.saveOrUpdate(customerCar);
            customerCar = new CustomerCar();
            versionId = 0;
            customerId = 0;
            driverId = 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "/index?faces-redirect=true";
    }
}