package model;

import model.exceptions.SallePleineException;

public class Concert {
    public String nom;

    private Room room;
    private double prixBillet;

    public Concert(String nom, String nomSalle, int nbPlaces, double prixBillet) {
        this.nom = nom;
        this.room = new Room(nomSalle, nbPlaces, nbPlaces);
        this.prixBillet = prixBillet;
    }

    public String getNom() {
        return nom;
    }

    public String getNomSalle() {
        return this.room.getName();
    }

    public int getNbPlaces() {
        return this.room.getNbPlaces();
    }

    public double getPrixBillet() {
        return prixBillet;
    }

    public int getPlacesDispo() {
        return this.room.getPlacesDispo();
    }

    public void setPrixBillet(double prixBillet) {
        this.prixBillet = prixBillet;
    }

    public void addReservation(Member m) throws SallePleineException {
        if (this.getPlacesDispo() <= 0) {
            throw new SallePleineException();
        }
        this.room.decrementPlacesDispo();
        m.addTicket(new Ticket(this));
    }

    public void cancelReservation(Member m) {
        this.room.incrementPlacesDispo();
        m.removeTicket(new Ticket(this));
    }

    public String toString() {
        return "Concert " + this.nom + " : " + this.getPlacesDispo() + " / " + this.getNbPlaces() + " places disponibles.";
    }

}
