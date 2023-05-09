package model;

import model.exceptions.*;

import java.util.ArrayList;

public class ClubManager {
    private RoomManager roomManager;
    private ArrayList<Club> clubs;
    public ClubManager(ArrayList<Room> rooms, ArrayList<Club> clubs) {
        this.roomManager = new RoomManager(rooms);
        this.clubs = clubs;
    }

    public ClubManager() {
        this(new ArrayList<>(), new ArrayList<>());
    }

    public ClubManager(ArrayList<Room> rooms) {
        this(rooms, new ArrayList<>());
    }

    public Club getClub(String clubName) throws UnknownClubException {
        for (Club club : getClubs()) {
            if (club.getName().toLowerCase().equals(clubName))
                return club;
        }
        throw new UnknownClubException(clubName);
    }

    public Member getMember(String memberName) throws UnknownMemberException {
        for (Club club : this.getClubs()) {
            try {
                return club.getMember(memberName);
            } catch (UnknownMemberException ignored) { }
        }
        throw new UnknownMemberException(memberName);
    }

    public RoomManager getRoomManager() {
        return this.roomManager;
    }

    public ArrayList<Club> getClubs() {
        return this.clubs;
    }

    public void addClubByName(String clubName) {
        Club club = new Club(clubName, this);
        this.clubs.add(club);
    }

    public void attemptReservation(Member member, Concert concert) throws FullConcertException, AlreadyBookedException {
        // Test if member already booked
        for (Ticket ticket : member.getTickets()) {
            if(ticket.getConcert() == concert) throw new AlreadyBookedException(ticket, member);
        }
        // Test if concert is full
        if(concert.getRoom().isFull()) throw new FullConcertException(concert);

        // Add new ticket
        member.addTicket(new Ticket(concert));
        concert.getRoom().decrementFreePlaces();
    }
    public ArrayList<Concert> getConcerts() {
        ArrayList<Concert> concerts = new ArrayList<>();
        for (Club club: getClubs()) {
            concerts.addAll(club.getConcerts());
        }
        return concerts;
    }
    public void addClub(Club club) {
        this.clubs.add(club);
    }
}
