package domain;

import interceptor.ErrorLoggingInterceptorBinding;
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
		@NamedQuery(name = "findAllMaintenances", query = "SELECT e FROM Maintenance e"),
		@NamedQuery(name = "findMaintenanceByBarcode" , query = "SELECT e FROM Maintenance e WHERE e.barcode = :barcode")
})
@ErrorLoggingInterceptorBinding
public class Maintenance {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	@ManyToOne
	@JoinColumn(name="customercar_id")
	private CustomerCar customerCar;
	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;
	@ManyToMany(fetch = FetchType.EAGER)
	private Collection<MaintenanceOption> maintenanceOptions;
	private String remark;
	private String barcode;
	private boolean apk;
	private boolean apkDone;
	@OneToMany(mappedBy = "maintenance")
	private Collection<Status> statuses;
}