package za.co.kmotsepe.tasuku.tickets.impl;

/**
 * Github ticket builder
 * @author Kingsley Motsepe
 */
public class GitHubTicketBuilder extends TicketBuilder{

    public GitHubTicketBuilder(){}

    public GitHubTicketBuilder id(int Id){
        this.Id = Id;
        return this;
    }

    public GitHubTicketBuilder name(String name){
        this.name = name;
        return this;
    }

    public GitHubTicketBuilder authorName(String authorName){
        this.authorName = authorName;
        return this;
    }

    public GitHubTicketBuilder severity(String severity){
        this.severity = severity;
        return this;
    }
    
    public GitHubTicketBuilder description(String description){
        this.description = description;
        return this;
    }
    
    public GitHubTicketBuilder category(String category){
        this.category = category;
        return this;
    }
}