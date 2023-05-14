package model;

import java.util.ArrayList;

import view.pages.ClubPage;

/**
 * A club enables to manage members and concerts.
 * A club knows its manager, its members and its concerts.
 * It can also organize concerts.
 */
public class Club implements ITicketListener {
    private String name;
    private ArrayList<Member> members;
    private ArrayList<IConcertListener> otherListeners;
    private ArrayList<Concert> concerts;
    private ClubPage window;

    public Club(String name, ArrayList<Member> members) {
        this.name = name;
        this.members = members;
        this.concerts = new ArrayList<>();
        this.otherListeners = new ArrayList<>();
        this.window = null;
    }

    public Club(String name) { this(name, new ArrayList<>()); }

    public String getName() {
        return name;
    }

    public void addMember(Member member) {
        this.members.add(member);
    }

    public void removeMember(Member member) {
        this.members.remove(member);
    }

    public void addConcertListener(IConcertListener listener) { otherListeners.add(listener); }

    /**
     * Adds a concert to the club and dispatches it as a ConcertEvent.
     *
     * @param concert The new concert to dispatch.
     */
    public void addConcert(Concert concert) {
        concerts.add(concert);
        ConcertEvent event = new ConcertEvent(this, concert);
        for (IConcertListener listener : members ) { listener.onNewConcert(event); }
        for (IConcertListener listener : otherListeners) { listener.onNewConcert(event); }
    }

    /**
     * Removes a concert from the club and dispatches it as a ConcertEvent.
     * @param concert The concert that got cancelled
     */
    public void removeConcert(Concert concert) {
        this.concerts.remove(concert);
        ConcertEvent event = new ConcertEvent(this, concert);
        for (IConcertListener listener : members) { listener.onConcertAnnulation(event); }
        for (IConcertListener listener : otherListeners) { listener.onConcertAnnulation(event); }
    }

    public ArrayList<Member> getMembers() { return this.members; }

    public ArrayList<Concert> getConcerts() { return this.concerts; }

    public void setWindow(ClubPage _window) { window = _window;}

    @Override
    public void onReservation(TicketEvent event) {
        if(window == null) return;
        window.updateConcerts();
    }

    @Override
    public void onAnnulation(TicketEvent event) {
        if(window == null) return;
        window.updateConcerts();
    }
}
