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
@Named("modelController")
public class ModelController {
    private Model currentModel;
    private long brandId;

    @EJB
    ModelRepository modelRepository;
    @EJB
    BrandRepository brandRepository;

    public ModelController(){}

    public String prepareCreate(){
        this.currentModel = new Model();
        this.brandId = 0;
        return "/model/modeldetails?faces-redirect=true";
    }

    public String prepareEdit(Model model){
        this.currentModel = model;
        this.brandId = model.getBrand().getId();
        return "/model/modeldetails?faces-redirect=true";
    }

    public String prepareList(){
        return "/model/modellist?faces-redirect=true";
    }

    public Model getModel(){
        return this.currentModel;
    }

    public long getBrandId(){
        return brandId;
    }

    public void setBrandId(long brandId){
        this.brandId = brandId;
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

    public List<Model> getModels(){
        List<Model> models = null;
        try {
            models = modelRepository.getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return models;
    }

    public String save(){
        try {
            this.currentModel.setBrand(brandRepository.getById(brandId));
            modelRepository.saveOrUpdate(this.currentModel);
            this.currentModel = new Model();
            brandId = 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/model/modellist?faces-redirect=true";
    }


}