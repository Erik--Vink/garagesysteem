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
		@NamedQuery(name = "findAllModels", query = "SELECT e FROM Model e")
})
@MyInterceptorBinding
public class Model {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String modelName;
	@ManyToOne
	@JoinColumn(name="brand_id")
	private Brand brand;
	@OneToMany(mappedBy = "model")
	private Collection<Version> versions;

}