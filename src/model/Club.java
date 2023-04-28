package model;
import java.util.ArrayList;

import controller.ClubManager;
import controller.ConcertEvent;
import controller.ConcertListener;

public class Club {
    private ClubManager manager;
    private ArrayList<Membre> membres;
    private ArrayList<Concert> concerts;

    public Club(ClubManager manager) {
        this.manager = manager;
        this.membres = new ArrayList<Membre>();
        this.concerts = new ArrayList<Concert>();
    }

    public Club(ClubManager manager, ArrayList<Membre> membres) {
        this.manager = manager;
        this.membres = membres;
        this.concerts = new ArrayList<Concert>();
    }

    public void addMembre(Membre m) {
        this.membres.add(m);
    }

    public void addConcert(Concert c) {
        this.concerts.add(c);
    }

    /**
     * Dispatches an event to warn members of a concert.
     * 
     * @param c Concert.
     */
    private void dispatchInformation(Concert c) {
        ConcertEvent event = new ConcertEvent(this, c);
        this.manager.onMembersInformed(event, membres);
    }

    /**
     * Dispatches an annulation event.
     * 
     * @param c Concert
     * @param m Membre
     */
    private void dispatchAnnulation(Concert c, Membre m) {
        ConcertEvent event = new ConcertEvent(this, c);
        this.manager.onAnnulation(event, m);
    }

    /**
     * Dispatches a reservation event.
     * 
     * @param c
     * @param m
     */
    private void dispatchReservation(Concert c, Membre m) {
        ConcertEvent event = new ConcertEvent(this, c);
        this.manager.onReservation(event, m);
    }

    public void informMembers(Concert c) {
        if (concerts.contains(c)) {
            dispatchInformation(c);
        }
    }

    public void cancelMemberReservation(Concert c, Membre m) {
        if (concerts.contains(c) && membres.contains(m)) {
            dispatchAnnulation(c, m);
        }
    }

    public void reserverBillet(Concert c, Membre m, String nomSalle) {
        if (c.getNomSalle() != nomSalle) {
            System.out.println("Nom de salle différent");
            return;
        }

        if (!concerts.contains(c) || !membres.contains(m)) {
            System.out.println("Concert ou membre non enregistré");
            return;
        }

        dispatchReservation(c, m);
    }
}
