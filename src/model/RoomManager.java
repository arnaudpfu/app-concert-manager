package model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Knows wich room is reserved by wich club.
 */
public class RoomManager {
    public static final String RESERVERD_KEY = "reserved";
    private HashMap<String, HashMap<String, Club>> rooms;

    public RoomManager() {
        this.rooms = new HashMap<String, HashMap<String, Club>>();
    }

    public RoomManager(ArrayList<String> roomNames) {
        this.rooms = new HashMap<String, HashMap<String, Club>>();
        for (String roomName : roomNames) {
            this.rooms.put(roomName, new HashMap<String, Club>());
        }
    }

    /**
     * Add a room to the manager.
     * 
     * @param roomName Name of the room.
     */
    public void addRoom(String roomName) {
        this.rooms.put(roomName, new HashMap<String, Club>());
    }

    /**
     * Remove a room from the room manager.
     * 
     * @param roomName Name of the room.
     * 
     * @exception RuntimeException if the room is reserved.
     */
    public void removeRoom(String roomName) throws RuntimeException {
        if( this.roomIsFree(roomName) ){
            this.rooms.remove(roomName);
        }

        throw new RuntimeException("Room " + roomName + " is reserved by " + this.rooms.get(roomName).get(RESERVERD_KEY).getName());
    }

    /**
     * Check if a club an reserve a room.
     * 
     * @param roomName Name of the room.
     * 
     * @return True if the room is reservable, false otherwise.
     */
    private boolean roomIsFree(String roomName) {
        return this.rooms.get(roomName).containsKey(RESERVERD_KEY);
    }

    /**
     * Reserve a room for a club.
     * 
     * @param roomName Name of the room.
     * @param club     Club that wants to reserve the room.
     * 
     * @throws RuntimeException if the room is already reserved.
     */
    public void reserveRoom(String roomName, Club club) throws RuntimeException {
        if (this.roomIsFree(roomName)) {
            this.rooms.get(roomName).put(RESERVERD_KEY, club);
        }
        
        throw new RuntimeException("Room " + roomName + " is already reserved by " + club.getName());
    }

    /**
     * Free a room.
     * 
     * @param roomName Name of the room.
     */
    public void freeRoom(String roomName) {
        this.rooms.get(roomName).remove(RESERVERD_KEY);
    }

}
