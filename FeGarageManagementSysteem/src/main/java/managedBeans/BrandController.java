package managedBeans;

import domain.Brand;
import domain.Customer;
import repositories.BrandRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;
import java.util.List;

/**
 * Created by Kenzo Dominicus on 23-11-2016.
 */
@Stateless
@Named("brandController")
public class BrandController {
    private Brand currentBrand;

    @EJB
    BrandRepository brandRepository;

    public BrandController(){}

    public String prepareCreate(){
        this.currentBrand = new Brand();
        return "/brand/branddetails?faces-redirect=true";
    }

    public String prepareEdit(Brand brand){
        this.currentBrand = brand;
        return "/brand/branddetails?faces-redirect=true";
    }

    public String prepareList(){
        return "/brand/brandlist?faces-redirect=true";
    }

    public Brand getBrand() {
        return this.currentBrand;
    }

    public List<Brand> getBrands(){
        try {
            return brandRepository.getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String save(){
        try {
            brandRepository.saveOrUpdate(this.currentBrand);
            this.currentBrand = new Brand();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/brand/brandlist?faces-redirect=true";
    }
}
