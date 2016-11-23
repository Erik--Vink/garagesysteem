package domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Kenzo Dominicus on 22-11-2016.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@NamedQueries({
		@NamedQuery(name = "findAllMaintenanceOptions", query = "SELECT e FROM MaintenanceOption e")
})
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