package model;

import model.exceptions.SallePleineException;

public class Concert {
    private String nom;
    private String nomSalle;
    private int nbPlaces;
    private int placesDispo;
    private double prixBillet;

    public Concert(String nom, String nomSalle, int nbPlaces, double prixBillet) {
        this.nom = nom;
        this.nomSalle = nomSalle;
        this.nbPlaces = nbPlaces;
        this.placesDispo = nbPlaces;
        this.prixBillet = prixBillet;
    }

    public String getNom() {
        return nom;
    }

    public String getNomSalle() {
        return this.nomSalle;
    }

    public int getNbPlaces() {
        return nbPlaces;
    }

    public double getPrixBillet() {
        return prixBillet;
    }

    public int getPlacesDipso() {
        return this.placesDispo;
    }

    public void setPrixBillet(double prixBillet) {
        this.prixBillet = prixBillet;
    }

    public void addReservation(Member m) throws SallePleineException {
        if (this.placesDispo <= 0) {
            throw new SallePleineException();
        }
        this.placesDispo -= 1;
        m.addTicket(new Ticket(this));
    }

    public void cancelReservation(Member m) {
        this.placesDispo += 1;
        m.removeTicket(new Ticket(this));
    }

    public String toString() {
        return "Concert " + this.nom + " : " + this.placesDispo + " / " + this.nbPlaces + " places disponibles.";
    }

}
