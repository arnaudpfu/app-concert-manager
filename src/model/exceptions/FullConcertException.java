package model.exceptions;

import model.Concert;
import model.Room;

public class FullConcertException extends Exception {
    public FullConcertException(Concert concert) {
        super("Le concert " + concert.getName() + " est plein !");
    }

}
