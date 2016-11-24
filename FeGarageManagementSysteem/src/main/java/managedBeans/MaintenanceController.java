package managedBeans;

import domain.Brand;
import domain.CustomerCar;
import domain.Maintenance;
import domain.MaintenanceOption;
import repositories.CustomerCarRepository;
import repositories.MaintenanceOptionRepository;
import repositories.MaintenanceRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Erik on 24-11-2016.
 */
@Named(value = "maintenanceController")
@Stateless
public class MaintenanceController {

    private Maintenance currentMaintenance;
    private long customerCarId;
    private long[] selectedMaintenanceOptions;

    @EJB
    MaintenanceRepository maintenanceRepository;

    @EJB
    CustomerCarRepository customerCarRepository;

    @EJB
    MaintenanceOptionRepository maintenanceOptionRepository;

    public MaintenanceController(){}

    public Maintenance getMaintenance(){
        return this.currentMaintenance;
    }

    public long getCustomerCarId(){
        return customerCarId;
    }

    public void setCustomerCarId(long customerCarId){
        this.customerCarId = customerCarId;
    }

    public long[] getSelectedMaintenanceOptions() {
        return selectedMaintenanceOptions;
    }

    public void setSelectedMaintenanceOptions(long[] selectedMaintenanceOptions) {
        this.selectedMaintenanceOptions = selectedMaintenanceOptions;
    }

    public List<CustomerCar> getCustomerCars(){
        List<CustomerCar> customerCars = null;
        try {
            customerCars = customerCarRepository.getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customerCars;
    }

    public List<MaintenanceOption> getMaintenanceOptions(){
        List<MaintenanceOption> maintenanceOptions = null;
        try {
            maintenanceOptions = maintenanceOptionRepository.getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return maintenanceOptions;
    }

    public String prepareCreate(){
        this.currentMaintenance = new Maintenance();
        this.customerCarId = 0;
        this.selectedMaintenanceOptions = new long[0];
        return "/maintenance/maintenancedetails?faces-redirect=true";
    }

    public String prepareEdit(Maintenance maintenance) throws Exception {
        this.currentMaintenance = maintenance;
        this.customerCarId = maintenance.getCustomerCar().getId();
        this.selectedMaintenanceOptions = maintenance.getMaintenanceOptions().stream().mapToLong(MaintenanceOption::getId).toArray();
        return "/maintenance/maintenancedetails?faces-redirect=true";
    }

    public String prepareList(){
        return "/maintenance/maintenancelist?faces-redirect=true";
    }


    public List<Maintenance> getMaintenances(){
        try {
            return maintenanceRepository.getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String save(){
        try {
            this.currentMaintenance.setCustomerCar(customerCarRepository.getById(this.customerCarId));
            ArrayList<MaintenanceOption> maintenanceOptions = new ArrayList<>();
            for (long id : this.selectedMaintenanceOptions) {
               maintenanceOptions.add(maintenanceOptionRepository.getById(id));
            }
            this.currentMaintenance.setMaintenanceOptions(maintenanceOptions);
            maintenanceRepository.saveOrUpdate(this.currentMaintenance);
            this.currentMaintenance = new Maintenance();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/maintenance/maintenancelist?faces-redirect=true";
    }
}

