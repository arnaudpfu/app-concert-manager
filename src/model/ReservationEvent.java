package model;
import java.util.EventObject;

public class ReservationEvent extends EventObject {
    private Concert concert;
    private Member member;

    public ReservationEvent(Object source, Concert concert, Member member) {
        super(source);
        this.concert = concert;
        this.member = member;
    }

    public Concert getConcert() {
        return concert;
    }
    public Member getMember() { return member; }
}
