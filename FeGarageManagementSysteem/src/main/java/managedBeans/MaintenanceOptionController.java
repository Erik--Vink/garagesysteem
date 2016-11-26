package managedBeans;

import domain.MaintenanceOption;
import interceptor.ErrorLoggingInterceptor;
import repositories.MaintenanceOptionRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.interceptor.Interceptors;
import java.util.List;

/**
 * Created by Erik on 24-11-2016.
 */
@Named(value = "maintenanceOptionController")
@Stateless
@Interceptors(ErrorLoggingInterceptor.class)
public class MaintenanceOptionController {

    private MaintenanceOption currentMaintenanceOption;

    @EJB
    MaintenanceOptionRepository maintenanceOptionRepository;

    public MaintenanceOptionController(){}

    public String prepareCreate(){
        this.currentMaintenanceOption = new MaintenanceOption();
        return "/maintenanceoption/maintenanceoptiondetails?faces-redirect=true";
    }

    public String prepareEdit(MaintenanceOption maintenanceOption){
        this.currentMaintenanceOption = maintenanceOption;
        return "/maintenanceoption/maintenanceoptiondetails?faces-redirect=true";
    }

    public String prepareList(){
        return "/maintenanceoption/maintenanceoptionlist?faces-redirect=true";
    }

    public MaintenanceOption getMaintenanceOption(){
        return this.currentMaintenanceOption;
    }

    public List<MaintenanceOption> getMaintenanceOptions() throws Exception {
        return maintenanceOptionRepository.getAll();
    }

    public String save(){

        maintenanceOptionRepository.saveOrUpdate(this.currentMaintenanceOption);
        this.currentMaintenanceOption = new MaintenanceOption();
        return "/maintenanceoption/maintenanceoptionlist?faces-redirect=true";
    }
}

