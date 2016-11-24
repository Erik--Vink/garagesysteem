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
		@NamedQuery(name = "findAllBrands", query = "SELECT e FROM Brand e")
})
@MyInterceptorBinding
public class Brand {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long Id;
	private String brandName;
	@OneToMany(mappedBy = "brand")
	private Collection<Model> models;
}