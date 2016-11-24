package domain;

import interceptor.MyInterceptorBinding;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

/**
 * Created by Kenzo Dominicus on 22-11-2016.
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Getter
@Setter
@NamedQueries({
		@NamedQuery(name = "findAllMaintenances", query = "SELECT e FROM Maintenance e")
})
@MyInterceptorBinding
public class Maintenance {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	@ManyToOne
	@JoinColumn(name="customercar_id")
	private CustomerCar customerCar;
	private Date startDate;
	@ManyToMany(fetch = FetchType.EAGER)
	private Collection<MaintenanceOption> maintenanceOptions;
	private String remark;
	private boolean apk;
	@OneToMany(mappedBy = "maintenance")
	private Collection<Status> statuses;
}