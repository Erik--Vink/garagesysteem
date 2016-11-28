package domain;

import interceptor.ErrorLoggingInterceptorBinding;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;

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
		@NamedQuery(name = "findAllMaintenanceOptions", query = "SELECT e FROM MaintenanceOption e")
})
@ErrorLoggingInterceptorBinding
public class MaintenanceOption {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String description;
	private double price;
	private int durationInMinutes;
	private String tasks;
	@ManyToMany(mappedBy = "maintenanceOptions")
	Collection<Maintenance> maintenances;

}