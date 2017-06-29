package za.co.kmotsepe.tasuku.aspectj;

import com.puppycrawl.tools.checkstyle.api.AuditEvent;
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

    @Pointcut("call(String addError(AuditEvent)) && args(ae) && target(callee)")
    public void addError(AuditEvent ae, CheckStyleListener checkStyleListener) {
    }

    @Around("addError(ae, callee)")
    public String upper(ProceedingJoinPoint joinPoint, 
            AuditEvent ae, CheckStyleListener callee) throws Throwable {
        Object[] args = new Object[3];
        args[0] = joinPoint.getThis();
        args[1] = callee;
        args[2] = ae.getMessage();
        String result = (String) joinPoint.proceed(args);
        
        System.err.println("intercepted addError" + args[2]);
        
        return result;
    }
}
