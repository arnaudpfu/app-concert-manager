package model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Knows wich room is reserved by wich club.
 */
public class RoomManager {
    public static final String RESERVERD_KEY = "reserved";
    private HashMap<Room, HashMap<String, Club>> rooms;

    public RoomManager() {
        this.rooms = new HashMap<Room, HashMap<String, Club>>();
    }

    public RoomManager(ArrayList<Room> rooms) {
        this.rooms = new HashMap<Room, HashMap<String, Club>>();
        for (Room room : rooms) {
            this.rooms.put(room, new HashMap<String, Club>());
        }
    }

    /**
     * Add a room to the manager.
     * 
     * @param room Room to add.
     */
    public void addRoom(Room room) {
        this.rooms.put(room, new HashMap<String, Club>());
    }

    /**
     * Remove a room from the room manager.
     * 
     * @param room Room to remove.
     * 
     * @exception RuntimeException if the room is reserved.
     */
    public void removeRoom(Room room) throws RuntimeException {
        if( this.roomIsFree(room) ){
            this.rooms.remove(room);
        }

        throw new RuntimeException("Room " + room.getName() + " is reserved by " + this.rooms.get(room).get(RESERVERD_KEY).getName());
    }

    /**
     * Check if a club an reserve a room.
     *
     * @param room Room  of the room.
     *
     * @return True if the room is reservable, false otherwise.
     */
    private boolean roomIsFree(Room room) {
        return this.rooms.get(room).containsKey(RESERVERD_KEY);
    }

    /**
     * Reserve a room for a club.
     * 
     * @param room     Room to reserve.
     * @param club     Club that wants to reserve the room.
     * 
     * @throws RuntimeException if the room is already reserved.
     */
    public void reserveRoom(Room room, Club club) throws RuntimeException {
        if (this.roomIsFree(room)) {
            this.rooms.get(room).put(RESERVERD_KEY, club);
        }
        
        throw new RuntimeException("Room " + room.getName() + " is already reserved by " + club.getName());
    }

    /**
     * Frees a room.
     * 
     * @param room Room to free.
     */
    public void freeRoom(Room room) {
        this.rooms.get(room).remove(RESERVERD_KEY);
    }

}
