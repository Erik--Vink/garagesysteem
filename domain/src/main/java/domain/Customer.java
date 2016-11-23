package domain;

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
		@NamedQuery(name = "findAllCustomers", query = "SELECT e FROM Customer e")
})
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Enumerated(EnumType.STRING)
	private CustomerType customerType;
	@OneToMany(mappedBy = "customer")
	private Collection<CustomerCar> customerCars;
	private String firstName;
	private String lastName;
	private String companyName;
	private String city;
	private String postalCode;
	private String streetAdress;
	private String phoneNumber;
	private String mailAdress;
	private String accountNumber;
	private String vatNumber;

}