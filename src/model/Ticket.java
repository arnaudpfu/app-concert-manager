package model;
public class Ticket {
    private Concert concert;

    public Ticket(Concert concert) {
        this.concert = concert;
    }

    public Concert getConcert() {
        return concert;
    }

    public String toString() {
        return "Billet pour le concert de " + this.concert.getName();
    }
}
