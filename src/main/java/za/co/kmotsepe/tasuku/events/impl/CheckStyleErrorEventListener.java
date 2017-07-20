/**
 * various code style events
 */
package za.co.kmotsepe.tasuku.events.impl;

import com.google.common.eventbus.Subscribe;
import com.puppycrawl.tools.checkstyle.api.AuditEvent;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import za.co.kmotsepe.tasuku.checkstyle.CheckStyleIssue;
import za.co.kmotsepe.tasuku.service.impl.GitHubTicketServiceImpl;
import za.co.kmotsepe.tasuku.tickets.IssueCategoryType;
import za.co.kmotsepe.tasuku.tickets.IssueSeverityType;
import za.co.kmotsepe.tasuku.tickets.impl.GitHubTicketBuilder;

/**
 *
 * @author Kingsley Motsepe
 */
public class CheckStyleErrorEventListener {

    /**
     * Application logger
     */
    private static final org.apache.log4j.Logger LOGGER
            = org.apache.log4j.Logger.getLogger(CheckStyleErrorEventListener.class);

    /**
     *
     * @param auditEvent Checkstyle audit event
     */
    @Subscribe
    public final void recordError(AuditEvent auditEvent) {
        GitHubTicketServiceImpl gitHubTicketService;
        GitHubTicketBuilder gitHubTicketBuilder;
        CheckStyleIssue checkStyleIssue;

        System.out.println("Creating new Github ticket");

        gitHubTicketBuilder = new GitHubTicketBuilder();

        try {
            gitHubTicketService = new GitHubTicketServiceImpl();
            checkStyleIssue = new CheckStyleIssue(auditEvent);
            gitHubTicketBuilder.name(checkStyleIssue.getName())
                    .authorName(CheckStyleIssue.AUTHOR)
                    .description(checkStyleIssue.getDescription())
                    .severity(IssueSeverityType.TRIVIAL)
                    .category(IssueCategoryType.BUG);
            gitHubTicketService.createTicket(gitHubTicketBuilder);

            TimeUnit.SECONDS.sleep(2);

        } catch (ClassNotFoundException | ClassCastException | IOException
                | InterruptedException ex) {
            LOGGER.error(ex);
        }
    }
}
