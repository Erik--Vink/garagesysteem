package interceptor;

import domain.DbLogger;
import repositories.DbLoggerRepository;

import javax.ejb.EJB;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

/**
 * Created by dewi on 24.11.16.
 */
@Interceptor
@MyInterceptorBinding
public class TestInterceptor {

    @EJB
    private DbLoggerRepository dbLoggerRepository;
    private DbLogger dbLogger = new DbLogger();

    @AroundInvoke
    public Object testInterceptor(InvocationContext invocationContext) throws Exception {
        System.out.println(" Interceptor calling to library method: " + invocationContext.getMethod().getName());

        Object result = null;
        try {
            result = invocationContext.proceed();
        } catch (Exception ex) {
            
            dbLogger.setExceptionType(ex.toString());
            dbLogger.setExceptionMessage(ex.getMessage());
            dbLoggerRepository.logException(dbLogger);
        }
        return result;
    }
}
