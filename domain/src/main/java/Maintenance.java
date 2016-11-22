import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

/**
 * Created by Kenzo Dominicus on 22-11-2016.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Maintenance {

	private long id;
	private CustomerCar customerCar;
	private LocalDateTime startDate;
	private List<MaintenanceOptions> maintenanceOptions;
	private String remark;
	private boolean apk;
	private Collection<Status> states;

}