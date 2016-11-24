package managedBeans;

import domain.Maintenance;
import domain.MaintenanceOption;
import repositories.MaintenanceOptionRepository;
import repositories.MaintenanceRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;
import java.util.List;

/**
 * Created by Erik on 24-11-2016.
 */
@Named(value = "maintenanceOptionController")
@Stateless
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

    public List<MaintenanceOption> getMaintenanceOptions(){
        try {
            return maintenanceOptionRepository.getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String save(){
        try {
            maintenanceOptionRepository.saveOrUpdate(this.currentMaintenanceOption);
            this.currentMaintenanceOption = new MaintenanceOption();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/maintenanceoption/maintenanceoptionlist?faces-redirect=true";
    }
}

