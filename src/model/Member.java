package model;

import view.pages.MemberPage;

import java.util.ArrayList;

public class Member {
    private String name;
    private double priceThreshold;
    private ArrayList<Ticket> tickets;

    private MemberPage window = null;

    public Member(String name, double priceThreshold) {
        this.name = name.toLowerCase();
        this.priceThreshold = priceThreshold;
        this.tickets = new ArrayList<>();
    }

    public void onNotifyNewConcert(Concert concert) {
        if(canReserve(concert)) System.out.println(getName() + " est intéressé par " + concert.getName());
        if(window != null) window.onNotifyNewConcert(concert);
    }
    public boolean canReserve(Concert concert) {
        return this.getPriceThreshold() >= concert.getTicketPrice();
    }

    public boolean hasNoTickets() { return this.getTickets().isEmpty(); }

    public String getName() {
        return name;
    }

    public double getPriceThreshold() {
        return priceThreshold;
    }

    public void addTicket(Ticket b) {
        this.tickets.add(b);
    }

    public void removeTicket(Ticket b) {
        this.tickets.remove(b);
    }

    public ArrayList<Ticket> getTickets() {
        return this.tickets;
    }

    public ArrayList<Concert> getReservedConcerts() {
        ArrayList<Concert> concerts = new ArrayList<>();
        for (Ticket ticket: getTickets()) {
            concerts.add(ticket.getConcert());
        }
        return concerts;
    }

    public boolean hasReserved(Concert concert) {
        return getReservedConcerts().contains(concert);
    }
    public String ticketsToString() {
        String s = this.name + " a acheté les billets suivants : \n";
        for (Ticket b : this.tickets) {
            s += b.toString() + "\n";
        }
        return s;
    }

    public MemberPage getWindow() { return window; }
    public void setWindow(MemberPage _window) { window = _window;}
}
