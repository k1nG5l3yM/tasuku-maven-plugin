/**
 * various service implementations
 */
package za.co.kmotsepe.tasuku.service;

import za.co.kmotsepe.tasuku.tickets.Ticket;

/**
 * @author Kingsley Motsepe
 */
public interface TicketService {

    /**
     * @param ticket
     */
    void createTicket(final Ticket ticket);
}
