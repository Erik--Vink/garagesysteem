package managedBeans;

import domain.Brand;
import repositories.BrandRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;

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
            brandRepository.saveOrUpdate(brand);
            brand = new Brand();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/index?faces-redirect=true";
    }


}
