package managedBeans;

import domain.CustomerCar;
import domain.Maintenance;
import domain.MaintenanceOption;
import interceptor.ErrorLoggingInterceptor;
import repositories.CustomerCarRepository;
import repositories.MaintenanceOptionRepository;
import repositories.MaintenanceRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.interceptor.Interceptors;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Erik on 24-11-2016.
 */
@Named(value = "maintenanceController")
@Stateless
@Interceptors(ErrorLoggingInterceptor.class)
public class MaintenanceController {

    private Maintenance currentMaintenance;
    private long customerCarId;
    private long[] selectedMaintenanceOptions;
    private int selectedStartHours;
    private int selectedStartMinutes;
    private String barcode, errorMessage;

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

    public int getSelectedStartHours() {
        return selectedStartHours;
    }

    public void setSelectedStartHours(int selectedStartHours) {
        this.selectedStartHours = selectedStartHours;
    }

    public int getSelectedStartMinutes() {
        return selectedStartMinutes;
    }

    public void setSelectedStartMinutes(int selectedStartMinutes) {
        this.selectedStartMinutes = selectedStartMinutes;
    }

    public long[] getSelectedMaintenanceOptions() {
        return selectedMaintenanceOptions;
    }

    public void setSelectedMaintenanceOptions(long[] selectedMaintenanceOptions) {
        this.selectedMaintenanceOptions = selectedMaintenanceOptions;
    }

    public String getBarcode(){
        return barcode;
    }

    public void setBarcode(String barcode){
        this.barcode = barcode;
    }

    public String getErrorMessage(){
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage){
        this.errorMessage = errorMessage;
    }

    public List<CustomerCar> getCustomerCars() throws Exception {
        return customerCarRepository.getAll();
    }

    public List<MaintenanceOption> getMaintenanceOptions() throws Exception {
        return maintenanceOptionRepository.getAll();
    }

    public String prepareCreate(){
        cleanClass();
        return "/maintenance/maintenancedetails?faces-redirect=true";
    }

    public String prepareEdit(Maintenance maintenance) throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(maintenance.getStartDate());
        this.selectedStartHours = calendar.get(Calendar.HOUR_OF_DAY);
        this.selectedStartMinutes = calendar.get(Calendar.MINUTE);
        this.currentMaintenance = maintenance;
        this.customerCarId = maintenance.getCustomerCar().getId();
        this.selectedMaintenanceOptions = maintenance.getMaintenanceOptions().stream().mapToLong(MaintenanceOption::getId).toArray();
        this.barcode = maintenance.getBarcode();
        return "/maintenance/maintenancedetails?faces-redirect=true";
    }

    public String prepareList(){
        return "/maintenance/maintenancelist?faces-redirect=true";
    }

    public String prepareSearch(){
        cleanClass();
        return "/maintenance/findmaintenance?faces-redirect=true";
    }


    public List<Maintenance> getMaintenances() throws Exception {
        return maintenanceRepository.getAll();
    }

    public String save() throws Exception {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.currentMaintenance.getStartDate());
        calendar.set(Calendar.HOUR_OF_DAY, this.selectedStartHours);
        calendar.set(Calendar.MINUTE, this.selectedStartMinutes);
        this.currentMaintenance.setStartDate(calendar.getTime());

        this.currentMaintenance.setCustomerCar(customerCarRepository.getById(this.customerCarId));
        ArrayList<MaintenanceOption> maintenanceOptions = new ArrayList<>();

        for (long id : this.selectedMaintenanceOptions) {
            maintenanceOptions.add(maintenanceOptionRepository.getById(id));
        }
        this.currentMaintenance.setMaintenanceOptions(maintenanceOptions);
        System.out.println(this.selectedStartHours);
        maintenanceRepository.saveOrUpdate(this.currentMaintenance);
        cleanClass();


        return "/maintenance/maintenancelist?faces-redirect=true";
    }

    public String searchMaintenance() throws Exception {
        try {
            Maintenance maintenance = maintenanceRepository.getByBarcode(barcode);
            this.currentMaintenance = maintenance;
            this.customerCarId = maintenance.getCustomerCar().getId();
            this.selectedMaintenanceOptions = maintenance.getMaintenanceOptions().stream().mapToLong(MaintenanceOption::getId).toArray();
            return "/maintenance/maintenancedetails?faces-redirect=true";
        } catch (Exception ex){
            errorMessage = ex.getMessage();
            return "/maintenance/findmaintenance?faces-redirect=true";
        }
    }

    private void cleanClass(){
        this.currentMaintenance = new Maintenance();
        this.customerCarId = 0;
        this.selectedMaintenanceOptions = new long[0];
        this.barcode = "";
        this.errorMessage = "";
    }
}

