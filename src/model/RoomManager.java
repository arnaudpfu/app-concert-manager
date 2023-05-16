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

    public void freeRoom(Concert concert) { concert.getRoom().bookedDates.remove(concert.getDate()); }
    public void bookRoom(Concert concert) { concert.getRoom().bookedDates.put(concert.getDate(), 0); }

    @Override
    public void onNewConcert(ConcertEvent event) {
        bookRoom(event.getConcert());
        Concert concert = event.getConcert();
        System.out.println("Booked room " + concert.getRoom().getName() + " (" + concert + ")");
        if(window != null) { window.updateRooms(); }
    }

    @Override
    public void onConcertAnnulation(ConcertEvent event) {
        freeRoom(event.getConcert());
        System.out.println("Freed room " + event.getConcert().getRoom().getName() + " on the " + event.getConcert().getDateFormat());
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

    public ArrayList<Room> getRooms() {
        return this.rooms;
    }
    public void setWindow(RoomManagerPage _window) { window = _window; }
}
