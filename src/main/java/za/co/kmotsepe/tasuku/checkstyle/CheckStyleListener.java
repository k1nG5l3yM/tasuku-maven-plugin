package za.co.kmotsepe.tasuku.checkstyle;

import com.puppycrawl.tools.checkstyle.api.AuditEvent;
import com.puppycrawl.tools.checkstyle.api.AuditListener;
import org.apache.log4j.Logger;

/**
 *
 * @author Kingsley Motsepe
 */
public class CheckStyleListener implements AuditListener{

    final static Logger LOGGER = Logger.getLogger(CheckStyleListener.class);

    @Override
    public void auditStarted(AuditEvent ae) {
        LOGGER.info("Checkstyle audit started");
    }

    @Override
    public void auditFinished(AuditEvent ae) {

    }

    @Override
    public void fileStarted(AuditEvent ae) {
        StringBuilder message = new StringBuilder();
        LOGGER.error(message.append("File started=>").append(ae.getFileName()).toString());
    }

    /**
     *
     * @param ae
     */
    @Override
    public void fileFinished(AuditEvent ae) {

    }

    @Override
    public void addError(AuditEvent ae) {
        StringBuilder message = new StringBuilder();
        LOGGER.error(message.append("File=>").append(ae.getFileName())
        .append("\nLine number=>").append(ae.getLine())
        .append("\nSeverity=>").append(ae.getSeverityLevel().getName()).toString());
    }

    @Override
    public void addException(AuditEvent ae, Throwable thrwbl) {
       LOGGER.error(thrwbl.getMessage());
    }
}
