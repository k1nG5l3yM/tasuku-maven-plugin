/**
 * Generic ticket classes plus specific ticket builders
 */
package za.co.kmotsepe.tasuku.tickets.impl;

import za.co.kmotsepe.tasuku.tickets.Ticket;

/**
 *
 * @author Kingsley Motsepe
 */
//TODO why is this not abstract? Will look at later
public class TicketBuilder extends TicketImpl {

    /**
     *
     * @return Ticket
     */
    public Ticket build() {
        return new TicketImpl(this);
    }
}
