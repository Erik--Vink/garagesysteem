package managedBeans;

import domain.Customer;
import domain.CustomerCar;
import domain.Driver;
import domain.Version;
import interceptor.ErrorLoggingInterceptor;
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
@Interceptors(ErrorLoggingInterceptor.class)
public class CustomerCarController {
    private long driverId, customerId, versionId;
    private CustomerCar currentCustomerCar;

    @EJB
    private VersionRepository versionRepository;
    @EJB
    private CustomerRepository customerRepository;
    @EJB
    private DriverRepository driverRepository;
    @EJB
    private CustomerCarRepository customerCarRepository;

    public CustomerCarController() {}

    public String prepareCreate(){
        this.currentCustomerCar = new CustomerCar();
        return "/customercar/customercardetails?faces-redirect=true";
    }

    public String prepareEdit(CustomerCar customerCar){
        this.currentCustomerCar = customerCar;
        this.driverId = customerCar.getDriver() != null ? customerCar.getDriver().getId(): 0;
        this.customerId = customerCar.getCustomer() != null ? customerCar.getCustomer().getId(): 0;
        this.versionId = customerCar.getVersion() != null ? customerCar.getVersion().getId(): 0;
        return "/customercar/customercardetails?faces-redirect=true";
    }

    public String prepareList(){
        return "/customercar/customercarlist?faces-redirect=true";
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
        return currentCustomerCar;
    }

    public void setCustomerCar(CustomerCar customerCar) {
        this.currentCustomerCar = customerCar;
    }

    public List<CustomerCar> getCustomerCars() throws Exception {
        return customerCarRepository.getAll();
    }

    public List<Version> getVersions() throws Exception {
        return versionRepository.getAll();
    }

    public List<Customer> getCustomers() throws Exception {
        return customerRepository.getAll();
    }

    public List<Driver> getDrivers() throws Exception {
        return driverRepository.getAll();
    }

    public String save() throws Exception {
        this.currentCustomerCar.setVersion(versionRepository.getById(versionId));
        this.currentCustomerCar.setCustomer(customerRepository.getById(customerId));
        if(driverId != 0){
            this.currentCustomerCar.setDriver(driverRepository.getById(driverId));
        }

        customerCarRepository.saveOrUpdate(this.currentCustomerCar);
        this.currentCustomerCar = new CustomerCar();
        this.versionId = 0;
        this.customerId = 0;
        this.driverId = 0;

        return "/customercar/customercarlist?faces-redirect=true";
    }
}