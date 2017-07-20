/**
 * AspectJ classes - Currently for checking the CheckStyleListener AuditEvent
 */
package za.co.kmotsepe.tasuku.aspectj;

import com.puppycrawl.tools.checkstyle.api.AuditEvent;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import za.co.kmotsepe.tasuku.checkstyle.CheckStyleListener;

/**
 *
 * @author Kingsley Motsepe
 */
@Aspect
public class CheckStyleListenerAspectJ {
    
    /**
     * Application logger
     */
    private static final Logger LOGGER = Logger.getLogger(CheckStyleListenerAspectJ.class);

    /**
     *
     * @param ae
     * @param checkStyleListener
     */
    @Pointcut("call(String addError(AuditEvent)) && args(ae) && target(callee)")
    public final void addError(final AuditEvent ae,
            final CheckStyleListener checkStyleListener) {
    }

    /**
     *
     * @param joinPoint
     * @param ae
     * @param callee
     * @return
     * @throws Throwable
     */
    @Around("addError(ae, callee)")
    public final String upper(final ProceedingJoinPoint joinPoint,
            final AuditEvent ae,
            final CheckStyleListener callee) throws Throwable {

        int argsCount = 3;
        Object[] args = new Object[argsCount];

        args[0] = joinPoint.getThis();
        args[1] = callee;
        args[2] = ae.getMessage();
        String result = (String) joinPoint.proceed(args);

        LOGGER.info("intercepted addError" + args[2]);

        return result;
    }
}
