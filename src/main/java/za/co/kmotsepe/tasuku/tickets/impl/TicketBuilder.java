/**
 * Generic ticket classes plus specific ticket builders
 */
package za.co.kmotsepe.tasuku.tickets.impl;

import za.co.kmotsepe.tasuku.tickets.Ticket;

/**
 *
 * @author Kingsley Motsepe
 */
public class TicketBuilder extends TicketImpl { //TODO why is this not abstract? Will look at later

    /**
     *
     * @return Ticket
     */
    public Ticket build() {
        return new TicketImpl(this);
    }
}
