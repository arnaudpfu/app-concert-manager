package model;

import java.util.ArrayList;

import controller.AssistantClub;
import controller.ConcertEvent;

/**
 * A club enables to manage members and concerts.
 * A club knows its manager, its members and its concerts.
 * It can also organize concerts.
 */
public class Club {
    private ClubManager clubManager;
    private String name;
    private ArrayList<Member> membres;
    /**
     * Execute actions when a concert event is triggered.
     */
    private AssistantClub manager;
    private ArrayList<Concert> concerts;

    public Club(String name, ClubManager clubManager) {
        this.name = name;
        this.clubManager = clubManager;
        this.membres = new ArrayList<Member>();
        this.manager = new AssistantClub();
        this.concerts = new ArrayList<Concert>();
    }

    public Club(String name, ClubManager clubManager, ArrayList<Member> membres) {
        this.name = name;
        this.clubManager = clubManager;
        this.membres = membres;
        this.manager = new AssistantClub();
        this.concerts = new ArrayList<Concert>();
    }

    public String getName() {
        return name;
    }

    public void addMembre(Member m) {
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
    private void dispatchAnnulation(Concert c, Member m) {
        ConcertEvent event = new ConcertEvent(this, c);
        this.manager.onAnnulation(event, m);
    }

    /**
     * Dispatches a reservation event.
     * 
     * @param c
     * @param m
     */
    private void dispatchReservation(Concert c, Member m) {
        ConcertEvent event = new ConcertEvent(this, c);
        this.manager.onReservation(event, m);
    }

    public void informMembers(Concert c) {
        if (concerts.contains(c)) {
            dispatchInformation(c);
        }
    }

    public void cancelMemberReservation(Concert c, Member m) {
        if (concerts.contains(c) && membres.contains(m)) {
            dispatchAnnulation(c, m);
        }
    }

    public void reserverBillet(Concert c, Member m, String nomSalle) {
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
