package domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
public class Maintenance {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	@ManyToOne
	@JoinColumn(name="customercar_id")
	private CustomerCar customerCar;
	private Date startDate;
	@ManyToMany
	private Collection<MaintenanceOption> maintenanceOptions;
	private String remark;
	private boolean apk;
	@OneToMany(mappedBy = "maintenance")
	private Collection<Status> statuses;
}