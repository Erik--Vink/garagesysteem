package managedBeans;

import domain.Model;
import domain.Version;
import repositories.ModelRepository;
import repositories.VersionRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;
import java.util.List;

/**
 * Created by dewi on 23.11.16.
 */
@Stateless
@Named("VersionEjb")
public class VersionEjb {

    private Version version;

    @EJB
    private VersionRepository versionRepository;
    @EJB
    private ModelRepository modelRepository;

    public VersionEjb() {
        this.version = new Version();
    }

    public Version getVersion() {
        return version;
    }

    public List<Model> getModels() {
        List<Model> models = null;
        try {
            models = modelRepository.getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return models;
    }

    public String save() {
        try {
            versionRepository.saveOrUpdate(version);
            version = new Version();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "/index?faces-redirect=true";
    }
}
