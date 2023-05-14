package model;

public interface ITicketListener {
    void onReservation(TicketEvent event);
    void onAnnulation(TicketEvent event);
}
