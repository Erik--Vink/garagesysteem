package domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@ManyToOne
	@JoinColumn(name="customercar_id")
	private CustomerCar customerCar;
	private LocalDateTime startDate;
	@ManyToMany
	private Collection<MaintenanceOptions> maintenanceOptions;
	private String remark;
	private boolean apk;
	@OneToMany(mappedBy = "status_id")
	private Collection<Status> states;

}