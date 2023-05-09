package model.exceptions;

import model.Room;

public class FullRoomException extends Exception {

    public FullRoomException(Room room) {
        super("La salle " + room.getName() + " est pleine !");
    }

}
