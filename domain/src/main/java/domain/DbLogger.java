package domain;

import interceptor.MyInterceptorBinding;
import lombok.*;

import javax.persistence.*;

/**
 * Created by dewi on 24.11.16.
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Getter
@Setter
@NamedQueries({
        @NamedQuery(name = "findAllDblogs", query = "SELECT e FROM DbLogger e")
})
@MyInterceptorBinding
public class DbLogger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String exceptionType;
    private String exceptionMessage;
}
