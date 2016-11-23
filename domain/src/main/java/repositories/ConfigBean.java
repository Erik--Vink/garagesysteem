package repositories;

import domain.*;

import javax.annotation.Generated;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

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

    @PostConstruct
    public void createData() throws Exception {

        // particulier
        Customer customer1 = customerRepository.save(Customer.builder()
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
        Customer customer2 = customerRepository.save(Customer.builder()
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


        Brand brand = brandRepository.save(Brand.builder()
                .brandName("Opel")
                .build());

        Brand brand1 = brandRepository.save(Brand.builder()
                .brandName("BMW")
                .build());


        Model model = modelRepository.save(Model.builder()
                .modelName("Corsa")
                .brand(brand)
                .build());

        Model model1 = modelRepository.save(Model.builder()
                .modelName("3 series")
                .brand(brand1)
                .build());


        Version version = versionRepository.save(Version.builder()
                .motor("1.8 Diesel")
                .versionName("Swing")
                .model(model)
                .build());

        Version version1 = versionRepository.save(Version.builder()
                .motor("2.0 Benzine")
                .versionName("328i")
                .model(model1)
                .build());

    }
}
