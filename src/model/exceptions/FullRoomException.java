package model.exceptions;
public class FullRoomException extends Exception {

    public FullRoomException() {
        super("La salle est pleine !");
    }

}
