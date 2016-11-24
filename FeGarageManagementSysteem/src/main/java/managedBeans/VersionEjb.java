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
    private long modelId;

    @EJB
    private VersionRepository versionRepository;
    @EJB
    private ModelRepository modelRepository;

    public VersionEjb() {
        this.version = new Version();
        this.modelId = 0;
    }

    public Version getVersion() {
        return version;
    }
    public long getModelId(){
        return modelId;
    }
    public void setModelId(long modelId){
        this.modelId = modelId;
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
            version.setModel(modelRepository.getById(modelId));
            versionRepository.saveOrUpdate(version);
            version = new Version();
            modelId = 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "/index?faces-redirect=true";
    }
}
