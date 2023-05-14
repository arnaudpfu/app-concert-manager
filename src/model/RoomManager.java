package model;

import model.exceptions.FullRoomException;
import model.exceptions.RoomAlreadyBookedException;
import view.pages.RoomManagerPage;

import java.util.ArrayList;
import java.util.Date;

/**
 * Knows which room is reserved by which club.
 */
public class RoomManager implements IConcertListener {
    private RoomManagerPage window = null;
    private ArrayList<Room> rooms;
    public RoomManager(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }

    public ArrayList<Room> getRooms() {
        return this.rooms;
    }

    /**
     * Reserve a room for a club.
     *
     * @param room                Room to reserve.
     * @param date                Date the concert takes place.
     * @throws FullRoomException  If the room is already reserved.
     */
    public void attemptRoomReservation(Room room, Date date) throws RoomAlreadyBookedException {
        room.book(date);
    }

    /**
     * Frees a room.
     * 
     * @param room Room to free.
     */
    public void freeRoom(Room room, Date date) { room.bookedDates.remove(date); }

    public void setWindow(RoomManagerPage _window) { window = _window; }

    @Override
    public void onNewConcert(ConcertEvent event) {
        if(window != null) { window.updateRooms(); }
    }

    @Override
    public void onConcertAnnulation(ConcertEvent event) {
        if(window != null) { window.updateRooms(); }
    }
}
