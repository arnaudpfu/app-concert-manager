package model;

import model.exceptions.FullRoomException;
import view.pages.RoomManagerPage;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Knows which room is reserved by which club.
 */
public class RoomManager {
    private RoomManagerPage window = null;
    private HashMap<Room, ArrayList<Date>> rooms;
    public RoomManager(ArrayList<Room> rooms) {
        this.rooms = new HashMap<>();
        for (Room room : rooms) {
            this.rooms.put(room, new ArrayList<>());
        }
    }

    public HashMap<Room, ArrayList<Date>> getRooms() {
        return this.rooms;
    }

    /**
     * Reserve a room for a club.
     *
     * @param room                Room to reserve.
     * @param date                Date the concert takes place.
     * @throws FullRoomException  If the room is already reserved.
     */
    public void attemptRoomReservation(Room room, Date date) throws FullRoomException {
        // Check if room is already booked on the date
        if (this.rooms.get(room).contains(date)) throw new FullRoomException(room, date);
        this.rooms.get(room).add(date);
    }

    /**
     * Frees a room.
     * 
     * @param room Room to free.
     */
    public void freeRoom(Room room, Date date) { this.rooms.get(room).remove(date); }

    public void notifyReservationChange() {
        if(window != null) { window.updateRooms(); }
    }
    public void setWindow(RoomManagerPage _window) { window = _window;}
}
