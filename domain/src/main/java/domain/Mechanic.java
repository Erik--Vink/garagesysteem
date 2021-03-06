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
		@NamedQuery(name = "findAllMechanics", query = "SELECT e FROM Mechanic e")
})
@ErrorLoggingInterceptorBinding
public class Mechanic {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String firstName;
	private String lastName;
	private String barcode;
	@OneToMany(mappedBy = "mechanic")
	private Collection<Status> statuses;
}