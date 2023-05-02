package model;

import java.util.ArrayList;

public class Member {
    private String name;
    private double priceThreshold;
    private ArrayList<Ticket> tickets;

    public Member(String name, double priceThreshold) {
        this.name = name.toLowerCase();
        this.priceThreshold = priceThreshold;
        this.tickets = new ArrayList<>();
    }
    public boolean canReserve(Concert concert) {
        return this.getPriceThreshold() >= concert.getTicketPrice();
    }
    public boolean hasNoTickets() { return this.getTickets().isEmpty(); }
    public String getName() {
        return name;
    }

    public double getPriceThreshold() { return priceThreshold; }

    public void addTicket(Ticket b) {
        this.tickets.add(b);
    }

    public void removeTicket(Ticket b) {
        this.tickets.remove(b);
    }

    public ArrayList<Ticket> getTickets() { return this.tickets; }
    public String ticketsToString() {
        String s = this.name + " a acheté les billets suivants : \n";
        for (Ticket b : this.tickets) {
            s += b.toString() + "\n";
        }
        return s;
    }
}
