package model;

import model.exceptions.FullRoomException;

public class Concert {
    public String nom;

    private Room room;
    private double ticketPrice;

    public Concert(String nom, String nomSalle, int nbPlaces, double ticketPrice) {
        this.nom = nom;
        this.room = new Room(nomSalle, nbPlaces, nbPlaces);
        this.ticketPrice = ticketPrice;
    }

    public void addReservation(Member m) throws FullRoomException {
        if (this.getNbFreePlaces() <= 0) {
            throw new FullRoomException();
        }
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

    public String getRoomName() {
        return this.room.getName();
    }

    public int getNbMaxPlaces() { return this.room.getNbMaxPlaces(); }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public int getNbFreePlaces() {
        return this.room.getNbFreePlaces();
    }

    public String toString() {
        return "Concert " + this.nom + " : " + this.getNbFreePlaces() + " / " + this.getNbMaxPlaces() + " places disponibles.";
    }

}
