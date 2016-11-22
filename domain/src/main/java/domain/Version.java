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
		@NamedQuery(name = "findAllVersions", query = "SELECT e FROM Version e")
})
public class Version {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String versionName;
	private String motor;
	@ManyToOne
	@JoinColumn(name="model_id")
	private Model model;
	@OneToMany(mappedBy = "customercar_id")
	private Collection<CustomerCar> customerCars;

}