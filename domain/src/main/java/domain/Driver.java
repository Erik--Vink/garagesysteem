package domain;

import interceptor.MyInterceptorBinding;
import lombok.*;

import javax.persistence.*;

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
		@NamedQuery(name = "findAllDrivers", query = "SELECT e FROM Driver e")
})
@MyInterceptorBinding
public class Driver {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	@OneToOne(mappedBy = "driver")
	private CustomerCar customerCar;

}