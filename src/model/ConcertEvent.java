package model;
import java.util.EventObject;

public class ConcertEvent extends EventObject {
    private Concert concert;

    public ConcertEvent(Object source, Concert concert) {
        super(source);
        this.concert = concert;
    }

    public Concert getConcert() {
        return concert;
    }
}
