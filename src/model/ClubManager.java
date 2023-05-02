package model;

import model.exceptions.UnknownClubException;
import model.exceptions.UnknownMemberException;

import java.util.ArrayList;

public class ClubManager {
    private RoomManager roomManager;
    private ArrayList<Club> clubs;

    public ClubManager() {
        this.roomManager = new RoomManager();
        this.clubs = new ArrayList<Club>();
    }

    public ClubManager(ArrayList<Club> clubs) {
        this.roomManager = new RoomManager();
        this.clubs = clubs;
    }

    public Club getClub(String clubName) throws UnknownClubException {
        for (Club club : getClubs()) {
            if (club.getName().toLowerCase().equals(clubName)) return club;
        }
        throw new UnknownClubException(clubName);
    }

    public Member getMember(String memberName) throws UnknownMemberException {
        for (Club club: this.getClubs()) {
            try {
                return club.getMember(memberName);
            } catch (UnknownMemberException e) {
                continue;
            }
        }
        throw new UnknownMemberException(memberName);
    }

    public RoomManager getRoomManager() { return this.roomManager; }

    public ArrayList<Club> getClubs() { return this.clubs; }
    public void addClub(Club club) { this.clubs.add(club); }
}
