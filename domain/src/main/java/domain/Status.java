package domain;

import interceptor.ErrorLoggingInterceptorBinding;
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
@ErrorLoggingInterceptorBinding
public class Status {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@ManyToOne
	@JoinColumn(name="maintenance_id")
	private Maintenance maintenance;
	@ManyToOne
	@JoinColumn(name="mechanic_id")
	private Mechanic mechanic;
	@Enumerated(EnumType.STRING)
	private StatusType statusType;

}