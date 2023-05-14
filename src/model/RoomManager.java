package model;

import view.pages.RoomManagerPage;

import java.util.ArrayList;

/**
 * Knows which room is reserved by which club.
 */
public class RoomManager implements IConcertListener, ITicketListener {
    private RoomManagerPage window = null;
    private ArrayList<Room> rooms;
    public RoomManager(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }

    public ArrayList<Room> getRooms() {
        return this.rooms;
    }

    public void freeRoom(Concert concert) { concert.getRoom().bookedDates.remove(concert.getDate()); }

    public void setWindow(RoomManagerPage _window) { window = _window; }

    @Override
    public void onNewConcert(ConcertEvent event) {
        if(window != null) { window.updateRooms(); }
    }

    @Override
    public void onConcertAnnulation(ConcertEvent event) {
        if(window != null) { window.updateRooms(); }
    }

    @Override
    public void onReservation(TicketEvent event) {
        if(window != null) { window.updateRooms(); }
    }

    @Override
    public void onAnnulation(TicketEvent event) {
        if(window != null) { window.updateRooms(); }
    }
}
