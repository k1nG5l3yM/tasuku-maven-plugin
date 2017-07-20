/**
 * Checkstyle related implementations
 */
package za.co.kmotsepe.tasuku.checkstyle;

import com.google.common.eventbus.EventBus;
import com.puppycrawl.tools.checkstyle.api.AuditEvent;
import com.puppycrawl.tools.checkstyle.api.AuditListener;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import za.co.kmotsepe.tasuku.events.impl.CheckStyleErrorEventListener;

/**
 *
 * @author Kingsley Motsepe
 */
public class CheckStyleListener implements AuditListener {

    /**
     * Guava EventBus
     */
    @Getter
    @Setter
    private EventBus eventBus;

    /**
     * Guava EventBus Listener
     */
    @Getter
    @Setter
    private CheckStyleErrorEventListener checkStyleErrorEventListener;

    /**
     * Application logger
     */
    private static final Logger LOGGER = Logger.getLogger(CheckStyleListener.class);

    /**
     *
     * @param ae AuditEvent
     */
    @Override
    public final void auditStarted(final AuditEvent ae) {
        LOGGER.info("Checkstyle audit started");
    }

    /**
     *
     * @param ae AuditEvent
     */
    @Override
    public final void auditFinished(final AuditEvent ae) {

    }

    /**
     *
     * @param ae AuditEvent
     */
    @Override
    public final void fileStarted(final AuditEvent ae) {
        StringBuilder message = new StringBuilder();
        LOGGER.info(message.append("File started=>")
                .append(ae.getFileName()).toString());
    }

    /**
     *
     * @param ae AuditEvent
     */
    @Override
    public final void fileFinished(final AuditEvent ae) {

    }

    /**
     *
     * @param ae AuditEvent
     */
    @Override
    public final void addError(final AuditEvent ae) {
        StringBuilder message = new StringBuilder();
        LOGGER.info(message.append("File=>")
                .append(FilenameUtils.getName(ae.getFileName()))
                .append("\nLine number=>").append(ae.getLine())
                .append("\nSeverity=>")
                .append(ae.getSeverityLevel().getName()));

        eventBus = new EventBus();
        checkStyleErrorEventListener = new CheckStyleErrorEventListener();

        eventBus.register(checkStyleErrorEventListener);
        eventBus.post(ae);
    }

    /**
     *
     * @param ae Checkstyle audit event
     * @param thrwbl exception
     */
    @Override
    public final void addException(final AuditEvent ae,
            final Throwable thrwbl) {
        LOGGER.error(thrwbl.getMessage());
    }
}
