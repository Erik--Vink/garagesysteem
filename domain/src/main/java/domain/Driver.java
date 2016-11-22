package domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by Kenzo Dominicus on 22-11-2016.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
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