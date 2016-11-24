package managedBeans;

import domain.Driver;
import interceptor.TestInterceptor;
import repositories.DriverRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.interceptor.Interceptors;

/**
 * Created by Kenzo Dominicus on 24-11-2016.
 */
@Stateless
@Named("DriverEjb")
@Interceptors(TestInterceptor.class)
public class DriverEjb {
    private Driver driver;

    @EJB
    DriverRepository driverRepository;

    public DriverEjb(){
        this.driver = new Driver();
    }

    public Driver getDriver(){
        return driver;
    }

    public String save(){

        driverRepository.saveOrUpdate(driver);
        driver = new Driver();
        return "/index?faces-redirect=true";
    }
}

