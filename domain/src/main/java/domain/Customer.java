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
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Enumerated(EnumType.STRING)
	private CustomerType customerType;
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