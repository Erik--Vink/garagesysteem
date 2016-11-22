package domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Kenzo Dominicus on 22-11-2016.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Brand {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long Id;
	private String brandName;
	@OneToMany(mappedBy = "model_id")
	private Collection<Model> models;
}