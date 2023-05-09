package model;

import java.util.ArrayList;

import model.exceptions.UnknownMemberException;

/**
 * A club enables to manage members and concerts.
 * A club knows its manager, its members and its concerts.
 * It can also organize concerts.
 */
public class Club {
    private String name;
    private ArrayList<Member> members;
    private AssistantClub manager;
    private ArrayList<Concert> concerts;

    public Club(String name) {
        this.name = name;
        this.members = new ArrayList<>();
        this.manager = new AssistantClub();
        this.concerts = new ArrayList<>();
    }

    public Club(String name, ClubManager clubManager, ArrayList<Member> members) {
        this.name = name;
        this.members = members;
        this.manager = new AssistantClub();
        this.concerts = new ArrayList<>();
        clubManager.addClub(this);
    }

    public String getName() {
        return name;
    }

    public void addMember(Member member) {
        this.members.add(member);
    }

    public void removeMember(Member member) {
        this.members.remove(member);
    }

    public void addConcert(Concert concert) {
        concerts.add(concert);
        if (concerts.contains(concert)) {
            dispatchInformation(concert);
        }
    }

    public void removeConcert(Concert concert) {
        this.concerts.remove(concert);
        dispatchConcertAnnulation(concert);
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
     * Dispatches a concert annulation.
     * @param concert The concert that got cancelled
     */
    private void dispatchConcertAnnulation(Concert concert) {
        ConcertEvent event = new ConcertEvent(this, concert);
        this.manager.onConcertAnnulation(event, members);
    }

    public ArrayList<Member> getMembers() {
        return this.members;
    }

    public ArrayList<Concert> getConcerts() { return this.concerts; }

    public Member getMember(String memberName) throws UnknownMemberException {
        for (Member member : members) {
            if (member.getName().equals(memberName))
                return member;
        }
        throw new UnknownMemberException(memberName);
    }
}
