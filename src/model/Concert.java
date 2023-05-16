package model;

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

    public boolean isFull() { return room.isFull(date); }
    public void incrementFreePlaces() { room.incrementFreePlaces(date); }
    public void decrementFreePlaces() { room.decrementFreePlaces(date); }
    public String getName() { return nom; }
    public Room getRoom() { return this.room; }
    public Date getDate() { return this.date; }
    public String getDateFormat() { return new SimpleDateFormat("dd/MM/yyyy").format(date); }
    public int getNbMaxPlaces() { return this.room.getNbMaxPlaces(); }
    public double getTicketPrice() { return ticketPrice; }
    public int getNbFreePlaces() { return this.room.getNbUnavailablePlaces(date); }
    public String toString() {
        return "Concert " + this.nom + " : " + this.getNbFreePlaces() + " / " + getNbMaxPlaces()  + " places";
    }
    public String getPlacesRatio() { return room.getPlacesRatio(date); }
}
