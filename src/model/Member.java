package model;

import java.util.ArrayList;

public class Member {
    private String name;
    private double prixSeuil;
    private ArrayList<Ticket> billets;

    public Member(String name, double prixSeuil) {
        this.name = name;
        this.prixSeuil = prixSeuil;
        this.billets = new ArrayList<Ticket>();
    }

    public String getName() {
        return name;
    }

    public double getPrixSeuil() {
        return prixSeuil;
    }

    public void addTicket(Ticket b) {
        this.billets.add(b);
    }

    public void removeTicket(Ticket b) {
        this.billets.remove(b);
    }

    public ArrayList<Ticket> getTickets() {
        return billets;
    }

    public String ticketsToString() {
        String s = this.name + " a acheté les billets suivants : \n";
        for (Ticket b : this.billets) {
            s += b.toString() + "\n";
        }
        return s;
    }
}
