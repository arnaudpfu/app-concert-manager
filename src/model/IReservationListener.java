package model;

public interface IReservationListener {
    void onReservation(ReservationEvent event);

    void onAnnulation(ReservationEvent event);
}
