package model;
import java.util.EventObject;

public class TicketEvent extends EventObject {
    private Ticket ticket;

    public TicketEvent(Object source, Ticket ticket) {
        super(source);
        this.ticket = ticket;
    }

    public Ticket getTicket() {
        return ticket;
    }
}
