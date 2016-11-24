package managedBeans;

import domain.Brand;
import domain.Driver;
import repositories.DriverRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;

/**
 * Created by Kenzo Dominicus on 24-11-2016.
 */
@Stateless
@Named("DriverEjb")
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
        try {
            driverRepository.saveOrUpdate(driver);
            driver = new Driver();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/index?faces-redirect=true";
    }
}

