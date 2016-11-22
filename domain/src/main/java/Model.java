import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

/**
 * Created by Kenzo Dominicus on 22-11-2016.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Model {

	private long id;
	private String modelName;
	private Brand brand;

}