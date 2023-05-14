package model.exceptions;

import model.Room;

public class RoomNotBookedException extends Exception {
    public RoomNotBookedException(Room room) {
        super("La salle \"" + room.getName() + "\" n'a jamais été réservé");
    }

}
