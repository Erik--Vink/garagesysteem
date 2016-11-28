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
@ErrorLoggingInterceptorBinding
public class ErrorLoggingInterceptor {

    @EJB
    private DbLoggerRepository dbLoggerRepository;
    private DbLogger dbLogger = new DbLogger();

    @AroundInvoke
    public Object errorInterceptor(InvocationContext invocationContext) throws Exception {
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
