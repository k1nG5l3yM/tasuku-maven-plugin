package za.co.kmotsepe.tasuku.tickets.impl;

import za.co.kmotsepe.tasuku.BaseObject;
import za.co.kmotsepe.tasuku.tickets.Ticket;

/**
 * @author Kingsley Motsepe
 */
public class TicketImpl extends BaseObject implements Ticket {
    int Id;
    String name;
    String description;
    String authorName;
    String severity;
    String category;
    
    /**
     * 
     */
    public TicketImpl(){}
    
    /**
     * 
     * @param ticketBuilder 
     */
    public TicketImpl(TicketBuilder ticketBuilder){
        this.Id = ticketBuilder.getId();
        this.description = ticketBuilder.getDescription();
        this.authorName = ticketBuilder.getAuthorName();
        this.severity = ticketBuilder.getSeverity();
    }

    /**
     * @return Ticket ID
     */
    public int getId(){
        return Id;
    }
    
    /**
     * 
     * @param name 
     */
    public void setName(String name){
        this.name = name;
    }
    
    public String getName(){
        return name;
    }

    /**
     * @return String Ticket author name
     */
    public String getAuthorName(){
        return name;
    }

    /**
     * @return String Ticket description
     */
    public String getDescription(){
        return description;
    }

    /**
     * @return <p>String Ticket severity level <p>This ranges from </p> 
     */
    public String getSeverity(){
        return severity;
    }
    
    /**
     * 
     * @return String Ticket category
     */
    public String getCategory(){
        return category;
    }
}