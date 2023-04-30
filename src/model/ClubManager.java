package model;

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

    public RoomManager getRoomManager() {
        return this.roomManager;
    }

    public ArrayList<Club> getClubs() {
        return this.clubs;
    }
}
