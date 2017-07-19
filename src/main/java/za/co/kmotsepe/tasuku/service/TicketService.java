package za.co.kmotsepe.tasuku.service;

import za.co.kmotsepe.tasuku.tickets.Ticket;

/**
 * @author Kingsley Motsepe
 * @param <T>
 */
public interface TicketService<T>{

    /**
     * @param ticket
     */
    public void createTicket(Ticket ticket);
}