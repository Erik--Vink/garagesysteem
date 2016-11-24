package managedBeans;

import domain.Brand;
import interceptor.TestInterceptor;
import repositories.BrandRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.interceptor.Interceptors;
import java.util.List;

/**
 * Created by Kenzo Dominicus on 23-11-2016.
 */
@Stateless
@Named("brandController")
@Interceptors(TestInterceptor.class)
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

    public List<Brand> getBrands() throws Exception {

        return brandRepository.getAll();
    }

    public String save(){

        brandRepository.saveOrUpdate(this.currentBrand);
        this.currentBrand = new Brand();
        return "/brand/brandlist?faces-redirect=true";
    }
}
