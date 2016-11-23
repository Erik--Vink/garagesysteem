package managedBeans;

import domain.Brand;
import repositories.BrandRepository;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.inject.Named;

/**
 * Created by Kenzo Dominicus on 23-11-2016.
 */
@Stateless
@Named("BrandEjb")
public class BrandEjb {

    @EJB
    BrandRepository brandRepository;

    public void save(Brand brand){
        try {
            brandRepository.save(brand);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
