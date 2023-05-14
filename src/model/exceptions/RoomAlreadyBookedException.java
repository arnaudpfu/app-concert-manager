package model.exceptions;

import model.Room;

public class RoomAlreadyBookedException extends Exception {
    public RoomAlreadyBookedException(Room room) {
        super("La salle \"" + room.getName() + "\" est déjà réservé par un club ce jour là");
    }

}
