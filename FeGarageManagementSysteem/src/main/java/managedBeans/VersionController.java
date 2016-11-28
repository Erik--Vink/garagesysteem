package managedBeans;

import domain.Model;
import domain.Version;
import interceptor.ErrorLoggingInterceptor;
import repositories.ModelRepository;
import repositories.VersionRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.interceptor.Interceptors;
import java.util.List;

/**
 * Created by dewi on 23.11.16.
 */
@Stateless
@Named("versionController")
@Interceptors(ErrorLoggingInterceptor.class)
public class VersionController {
    private Version currentVersion;
    private long modelId;

    @EJB
    private VersionRepository versionRepository;
    @EJB
    private ModelRepository modelRepository;

    public VersionController() {
    }

    public String prepareCreate(){
        this.currentVersion = new Version();
        return "/version/versiondetails?faces-redirect=true";
    }

    public String prepareEdit(Version version){
        this.currentVersion = version;
        return "/version/versiondetails?faces-redirect=true";
    }

    public String prepareList(){
        return "/version/versionlist?faces-redirect=true";
    }

    public Version getVersion() {
        return currentVersion;
    }
    public long getModelId(){
        return modelId;
    }
    public void setModelId(long modelId){
        this.modelId = modelId;
    }

    public List<Model> getModels() throws Exception {
        return modelRepository.getAll();
    }

    public List<Version> getVersions() throws Exception {
        return versionRepository.getAll();
    }

    public String save() throws Exception {

        currentVersion.setModel(modelRepository.getById(modelId));
        versionRepository.saveOrUpdate(currentVersion);
        currentVersion = new Version();
        modelId = 0;
        return "/version/versionlist?faces-redirect=true";
    }
}
