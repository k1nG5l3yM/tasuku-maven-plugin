/**
 * various service layers for interacting/integration with online service
 */
package za.co.kmotsepe.tasuku.service.impl;

import com.jcabi.github.Coordinates;
import com.jcabi.github.Github;
import com.jcabi.github.Issue;
import com.jcabi.github.Repo;
import com.jcabi.github.RtGithub;
import com.jcabi.github.wire.CarefulWire;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import za.co.kmotsepe.tasuku.service.TicketService;
import za.co.kmotsepe.tasuku.tickets.Ticket;
import za.co.kmotsepe.tasuku.tickets.impl.GitHubTicketBuilder;
import za.co.kmotsepe.tasuku.util.PropertiesLoader;

/**
 * Ticket service for creating github based tickets
 *
 * @author Kingsley Motsepe
 */
public class GitHubTicketServiceImpl implements TicketService {

    /**
     * Application logger
     */
    private static final org.apache.log4j.Logger LOGGER
            = org.apache.log4j.Logger.getLogger(GitHubTicketServiceImpl.class);

    /**
     * application properties
     */
    private final Properties properties;

    /**
     *
     * @throws IOException
     */
    public GitHubTicketServiceImpl() throws IOException {
        properties = PropertiesLoader.getInstance().loadProperties();
    }

    /**
     *
     * @param ticket
     */
    @Override
    public final void createTicket(final Ticket ticket) {
        GitHubTicketBuilder gitHubTicketBuilder = (GitHubTicketBuilder) ticket;
        ArrayList gitHubIssueLabels = new ArrayList();

        gitHubIssueLabels.add(gitHubTicketBuilder.getCategory());

        Github github = new RtGithub(
                new RtGithub(properties.getProperty("github.token"))
                        .entry().through(CarefulWire.class, 5000));

        Repo repo = github.repos()
                .get(new Coordinates.Simple(properties
                        .getProperty("github.coordinates.username"),
                        properties
                                .getProperty("github.coordinates.repository")));
        Issue issue;

        try {
            issue = repo.issues()
                    .create(gitHubTicketBuilder.getName(),
                            gitHubTicketBuilder.getDescription());
            issue.labels().add(gitHubIssueLabels);
        } catch (IOException ex) {
            LOGGER.error(ex);
        }
    }
}
