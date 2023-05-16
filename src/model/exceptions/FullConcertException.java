package model.exceptions;

import model.Concert;

public class FullConcertException extends Exception {
    public FullConcertException(Concert concert) {
        super("Le concert " + concert.getName() + " est plein !");
    }

}
