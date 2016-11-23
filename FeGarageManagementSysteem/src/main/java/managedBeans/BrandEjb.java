package managedBeans;

import domain.Brand;
import repositories.BrandRepository;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.inject.Named;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Kenzo Dominicus on 23-11-2016.
 */
@Stateless
@Named("BrandEjb")
public class BrandEjb {
    private Brand brand;

    @EJB
    BrandRepository brandRepository;

    public BrandEjb(){
        this.brand = new Brand();
    }

    public Brand getBrand() {
        return brand;
    }

    public String save(){
        try {
            brandRepository.save(brand);
            brand = new Brand();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/index?faces-redirect=true";
    }


}
