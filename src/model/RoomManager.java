package model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Knows which room is reserved by which club.
 */
public class RoomManager {
    private HashMap<Room, Club> rooms;

    public RoomManager() {
        this.rooms = new HashMap<>();
    }

    public RoomManager(ArrayList<Room> rooms) {
        this.rooms = new HashMap<>();
        for (Room room : rooms) {
            this.rooms.put(room, null);
        }
    }

    public HashMap<Room, Club> getRooms() {
        return this.rooms;
    }

    /**
     * Check if a club an reserve a room.
     *
     * @param room Room of the room.
     *
     * @return True if the room is reservable, false otherwise.
     */
    private boolean roomIsFree(Room room) {
        return this.rooms.get(room) == null;
    }

    /**
     * Reserve a room for a club.
     * 
     * @param room Room to reserve.
     * @param club Club that wants to reserve the room.
     * 
     * @throws RuntimeException if the room is already reserved.
     */
    public void reserveRoom(Room room, Club club) throws RuntimeException {
        if (this.roomIsFree(room)) {
            this.rooms.put(room, club);
        }

        throw new RuntimeException("Room " + room.getName() + " is already reserved by " + club.getName());
    }

    /**
     * Frees a room.
     * 
     * @param room Room to free.
     */
    public void freeRoom(Room room) {
        this.rooms.put(room, null);
    }

}
