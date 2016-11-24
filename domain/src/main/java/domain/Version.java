package domain;

import interceptor.MyInterceptorBinding;
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
		@NamedQuery(name = "findAllVersions", query = "SELECT e FROM Version e")
})
@MyInterceptorBinding
public class Version {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String versionName;
	private String motor;
	@ManyToOne
	@JoinColumn(name="model_id")
	private Model model;
	@OneToMany(mappedBy = "version")
	private Collection<CustomerCar> customerCars;

}