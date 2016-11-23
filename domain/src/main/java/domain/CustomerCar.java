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
		@NamedQuery(name = "findAllCustomerCars", query = "SELECT e FROM CustomerCar e")
})
public class CustomerCar {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String numberPlate;
	@ManyToOne
	@JoinColumn(name="version_id")
	private Version version;
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer customer;
	@OneToOne
	@JoinColumn(name="driver_id")
	private Driver driver;
	@OneToMany(mappedBy = "customerCar")
	private Collection<Maintenance> maintenances;

}