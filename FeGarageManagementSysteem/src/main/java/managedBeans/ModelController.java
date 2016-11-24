package managedBeans;

import domain.Brand;
import domain.Model;
import interceptor.TestInterceptor;
import repositories.BrandRepository;
import repositories.ModelRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.interceptor.Interceptors;
import java.util.List;

/**
 * Created by Kenzo Dominicus on 23-11-2016.
 */
@Stateless
@Named("modelController")
@Interceptors(TestInterceptor.class)
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

    public List<Brand> getBrands() throws Exception {

        return brandRepository.getAll();
    }

    public List<Model> getModels() throws Exception {

        return modelRepository.getAll();
    }

    public String save() throws Exception {

        this.currentModel.setBrand(brandRepository.getById(brandId));
        modelRepository.saveOrUpdate(this.currentModel);
        this.currentModel = new Model();
        brandId = 0;

        return "/model/modellist?faces-redirect=true";
    }


}