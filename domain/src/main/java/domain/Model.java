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
@NamedQueries({
		@NamedQuery(name = "findAllModels", query = "SELECT e FROM Model e")
})
public class Model {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String modelName;
	private Brand brand;

}