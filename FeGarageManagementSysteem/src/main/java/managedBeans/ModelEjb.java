package managedBeans;

import domain.Brand;
import domain.Model;
import repositories.BrandRepository;
import repositories.ModelRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;
import java.util.List;

/**
 * Created by Kenzo Dominicus on 23-11-2016.
 */
@Stateless
@Named("ModelEjb")
public class ModelEjb {
    private Model model;

    @EJB
    ModelRepository modelRepository;
    @EJB
    BrandRepository brandRepository;

    public ModelEjb(){
        this.model = new Model();
    }

    public Model getModel(){
        return model;
    }

    public List<Brand> getBrands(){
        List<Brand> brands = null;
        try {
            brands = brandRepository.getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return brands;
    }

    public String save(){
        try {
            modelRepository.saveOrUpdate(model);
            model = new Model();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/index?faces-redirect=true";
    }


}