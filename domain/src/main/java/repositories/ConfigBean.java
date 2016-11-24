package repositories;

import domain.*;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

@Singleton
@Startup
public class ConfigBean {

    @EJB
    private CustomerRepository customerRepository;
    @EJB
    private VersionRepository versionRepository;
    @EJB
    private BrandRepository brandRepository;
    @EJB
    private ModelRepository modelRepository;
    @EJB
    private CustomerCarRepository customerCarRepository;
    @EJB
    private MaintenanceOptionRepository maintenanceOptionRepository;
    @EJB
    private MaintenanceRepository maintenanceRepository;
    @EJB
    private DriverRepository driverRepository;
    @EJB
    private MechanicRepository mechanicRepository;
    @EJB
    private StatusRepository statusRepository;

    @PostConstruct
    public void createData() {


        // particulier
        Customer privateCustomer = customerRepository.saveOrUpdate(Customer.builder()
                .accountNumber("0123456789")
                .city("Heerlen")
                .customerType(CustomerType.PRIVATE)
                .firstName("Tom")
                .lastName("Cool")
                .mailAdress("tomcool@hotmail.com")
                .phoneNumber("9876543210")
                .postalCode("6369CR")
                .streetAdress("Diddenstraat 11")
                .build());

        // bedrijf
        Customer company = customerRepository.saveOrUpdate(Customer.builder()
                .accountNumber("7894561230")
                .city("Rotterdam")
                .companyName("GitGood")
                .customerType(CustomerType.CORPORATE)
                .mailAdress("gitgood@hotmail.com")
                .phoneNumber("3216549870")
                .postalCode("4578TR")
                .streetAdress("Milderstraat 55")
                .vatNumber("NL001234567B01")
                .build());

        MaintenanceOption maintenanceOption2 = maintenanceOptionRepository.saveOrUpdate(MaintenanceOption.builder()
                .description("Grote Beurt")
                .durationInMinutes(120)
                .price(100.00)
                .tasks("Remmen vervangen, Filters vervangen")
                .build());

        Collection<MaintenanceOption> maintenanceOptions = new ArrayList<>();
        maintenanceOptions.add(maintenanceOption2);


        Brand opel = brandRepository.saveOrUpdate(Brand.builder()
                .brandName("Opel")
                .build());

        Brand bmw = brandRepository.saveOrUpdate(Brand.builder()
                .brandName("BMW")
                .build());


        Model corsa = modelRepository.saveOrUpdate(Model.builder()
                .modelName("Corsa")
                .brand(opel)
                .build());

        Model threeSeries = modelRepository.saveOrUpdate(Model.builder()
                .modelName("3 series")
                .brand(bmw)
                .build());

        Model zafira = modelRepository.saveOrUpdate(Model.builder()
                .modelName("Zafira")
                .brand(opel)
                .build());


        Version opelSwing = versionRepository.saveOrUpdate(Version.builder()
                .motor("1.8 Diesel")
                .versionName("Swing")
                .model(corsa)
                .build());

        Version opelZafira = versionRepository.saveOrUpdate(Version.builder()
                .motor("1.6 Benzine")
                .versionName("Tourer C")
                .model(zafira)
                .build());

        Version bmw328i = versionRepository.saveOrUpdate(Version.builder()
                .motor("2.0 Benzine")
                .versionName("328i")
                .model(threeSeries)
                .build());


        CustomerCar customerCar = customerCarRepository.saveOrUpdate(CustomerCar.builder()
                .numberPlate("9-XXX-4")
                .customer(privateCustomer)
                .version(opelSwing)
                .build());

        Driver companyDriver = driverRepository.saveOrUpdate(Driver.builder()
                .firstName("Jan")
                .lastName("Jansen")
                .phoneNumber("9873216540")
                .build());

        CustomerCar driverCar = customerCarRepository.saveOrUpdate(CustomerCar.builder()
                .numberPlate("19-GR-LL")
                .driver(companyDriver)
                .version(opelZafira)
                .build());

        Maintenance maintenanceCustomer = maintenanceRepository.saveOrUpdate(Maintenance.builder()
                .apk(true)
                .remark("None")
                .startDate(java.sql.Date.valueOf(LocalDate.now()))
                .customerCar(customerCar)
                .maintenanceOptions(maintenanceOptions)
                .build());

        Collection<Maintenance> maintenances = new ArrayList<>();
        maintenances.add(maintenanceCustomer);

        MaintenanceOption maintenanceOption = maintenanceOptionRepository.saveOrUpdate(MaintenanceOption.builder()
                .description("Kleine Beurt")
                .durationInMinutes(60)
                .price(50.00)
                .tasks("Olie verversen, Filters vervangen")
                .maintenances(maintenances)
                .build());


        Mechanic mechanic = mechanicRepository.saveOrUpdate(Mechanic.builder()
                .firstName("Yus")
                .lastName("Yusuf")
                .build());

        Status statusMaintanance = statusRepository.saveOrUpdate(Status.builder()
                .statusType(StatusType.AFSPRAAK)
                .maintenance(maintenanceCustomer)
                .mechanic(mechanic)
                .build());

    }
}
