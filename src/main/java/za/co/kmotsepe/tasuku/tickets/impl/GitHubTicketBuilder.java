/**
 * Generic ticket classes plus specific ticket builders
 */
package za.co.kmotsepe.tasuku.tickets.impl;

/**
 * Github ticket builder
 *
 * @author Kingsley Motsepe
 */
public class GitHubTicketBuilder extends TicketBuilder {

    /**
     * default constructor
     */
    public GitHubTicketBuilder() {
    }

    /**
     *
     * @param id
     * @return
     */
    public GitHubTicketBuilder id(int id) {
        this.id = id;
        return this;
    }

    /**
     *
     * @param name
     * @return
     */
    public GitHubTicketBuilder name(String name) {
        this.name = name;
        return this;
    }

    /**
     *
     * @param authorName
     * @return
     */
    public GitHubTicketBuilder authorName(String authorName) {
        this.authorName = authorName;
        return this;
    }

    /**
     *
     * @param severity
     * @return
     */
    public GitHubTicketBuilder severity(String severity) {
        this.severity = severity;
        return this;
    }

    /**
     *
     * @param description
     * @return
     */
    public GitHubTicketBuilder description(String description) {
        this.description = description;
        return this;
    }

    /**
     *
     * @param category
     * @return
     */
    public GitHubTicketBuilder category(String category) {
        this.category = category;
        return this;
    }
}
