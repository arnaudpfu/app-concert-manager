package model.exceptions;

import model.Room;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FullRoomException extends Exception {
    public FullRoomException(Room room, Date date) {
        super("La salle " + room.getName() + " est pleine le " + new SimpleDateFormat("dd/MM/yyyy").format(date) + " !");
    }
    public FullRoomException(Room room) {
        super("La salle " + room.getName() + " est pleine !");
    }
}
