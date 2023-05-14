package model.exceptions;

import model.Member;
import model.Room;
import model.Ticket;

public class RoomAlreadyBookedException extends Exception {
    public RoomAlreadyBookedException(Room room) {
        super("La salle \"" + room.getName() + "\" est déjà réservé par un club ce jour là");
    }

}
