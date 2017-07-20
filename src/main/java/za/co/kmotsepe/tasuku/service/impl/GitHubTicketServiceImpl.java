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
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.Getter;
import lombok.Setter;

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
     * Ticket builder for the github service
     */
    @Getter
    @Setter
    private GitHubTicketBuilder gitHubTicketBuilder;

    /**
     * issue labels
     */
    @Getter
    @Setter
    private ArrayList gitHubIssueLabels;

    /**
     * application properties
     */
    private Properties properties;

    /**
     * list of github tickets
     */
    private ArrayList<Ticket> githubTickets;

    /**
     *
     * @throws IOException
     */
    public GitHubTicketServiceImpl() throws IOException {
        properties = PropertiesLoader.getInstance().loadProperties();
    }

    /**
     *
     * @param gitHubTicket
     */
    public GitHubTicketServiceImpl(final Ticket gitHubTicket) {

    }

    /**
     *
     * @param gitHubTickets
     */
    public GitHubTicketServiceImpl(final ArrayList<Ticket> gitHubTickets) {

    }

    /**
     *
     * @param ticket
     */
    @Override
    public final void createTicket(final Ticket ticket) {
        gitHubTicketBuilder = (GitHubTicketBuilder) ticket;

        gitHubIssueLabels = new ArrayList();
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
            Logger.getLogger(GitHubTicketServiceImpl.class.getName())
                    .log(Level.SEVERE, null, ex);
        }

    }

    /**
     *
     * @param gitHubTickets - a list of GitHub tickets
     */
    public void createTickets(final ArrayList<Ticket> gitHubTickets) {

    }
}
