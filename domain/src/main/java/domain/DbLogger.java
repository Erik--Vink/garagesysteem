package domain;

import interceptor.ErrorLoggingInterceptorBinding;
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
@ErrorLoggingInterceptorBinding
public class DbLogger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String exceptionType;
    private String exceptionMessage;
}
