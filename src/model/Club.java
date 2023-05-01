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
    private ArrayList<Member> members;
    /**
     * Execute actions when a concert event is triggered.
     */
    private AssistantClub manager;
    private ArrayList<Concert> concerts;

    public Club(String name, ClubManager clubManager) {
        this.name = name;
        this.clubManager = clubManager;
        this.members = new ArrayList<>();
        this.manager = new AssistantClub();
        this.concerts = new ArrayList<>();
    }

    public Club(String name, ClubManager clubManager, ArrayList<Member> members) {
        this.name = name;
        this.clubManager = clubManager;
        this.members = members;
        this.manager = new AssistantClub();
        this.concerts = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addMembre(Member m) {
        this.members.add(m);
    }

    public void addConcert(Concert c) {
        this.concerts.add(c);
    }

    /**
     * Dispatches an event to warn members of a concert.
     * 
     * @param concert Concert to dispatch.
     */
    private void dispatchInformation(Concert concert) {
        ConcertEvent event = new ConcertEvent(this, concert);
        this.manager.onMembersInformed(event, members);
    }

    /**
     * Dispatches an annulation event.
     *
     * @param member  The member that canceled its reservation
     * @param concert The related concert
     */
    private void dispatchAnnulation(Member member, Concert concert) {
        ConcertEvent event = new ConcertEvent(this, concert);
        this.manager.onAnnulation(event, member);
    }

    /**
     * Dispatches a reservation event.
     * 
     * @param concert The concert that was reserved
     * @param member The member that made a reservation
     */
    private void dispatchReservation(Concert concert, Member member) {
        ConcertEvent event = new ConcertEvent(this, concert);
        this.manager.onReservation(event, member);
    }

    public void informMembers(Concert c) {
        if (concerts.contains(c)) {
            dispatchInformation(c);
        }
    }

    public void cancelMemberReservation(Concert c, Member m) {
        if (concerts.contains(c) && members.contains(m)) {
            dispatchAnnulation(m, c);
        }
    }

    public void reserverBillet(Concert c, Member m, String nomSalle) {
        if (c.getRoomName() != nomSalle) {
            System.out.println("Nom de salle différent");
            return;
        }

        if (!concerts.contains(c) || !members.contains(m)) {
            System.out.println("Concert ou membre non enregistré");
            return;
        }

        dispatchReservation(c, m);
    }
}
