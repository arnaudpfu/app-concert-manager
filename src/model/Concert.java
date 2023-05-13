package model;

// import java.time.LocalDate;

import model.exceptions.FullRoomException;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Concert {
    public String nom;
    private Room room;
    private double ticketPrice;
    private Date date;

    public Concert(String nom, Room room, double ticketPrice, Date date) {
        this.nom = nom;
        this.room = room;
        this.ticketPrice = ticketPrice;
        this.date = date;
    }

    public void addReservation(Member m) throws FullRoomException {
        if (this.getNbFreePlaces() <= 0) { throw new FullRoomException(this.getRoom()); }
        this.room.decrementFreePlaces();
        m.addTicket(new Ticket(this));
    }

    public void cancelReservation(Member m) {
        this.room.incrementFreePlaces();
        m.removeTicket(new Ticket(this));
    }

    public String getName() {
        return nom;
    }
    public Room getRoom() { return this.room; }
    public Date getDate() { return this.date; }
    public String getDateFormat() { return new SimpleDateFormat("dd/MM/yyyy").format(date); }
    public int getNbMaxPlaces() {
        return this.room.getNbMaxPlaces();
    }
    public double getTicketPrice() {
        return ticketPrice;
    }
    public int getNbFreePlaces() {
        return this.room.getNbFreePlaces();
    }
    public String toString() {
        return "Concert " + this.nom + " : " + this.getNbFreePlaces() + " / " + this.getNbMaxPlaces()
                + " places disponibles.";
    }

}
