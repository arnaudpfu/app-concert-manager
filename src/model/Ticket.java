package model;
public class Ticket {
    private Concert concert;
    private Member member;

    public Ticket(Concert concert, Member member) {
        this.concert = concert;
        this.member = member;
    }

    public Concert getConcert() {
        return concert;
    }
    public Member getMember() {
        return member;
    }

    public String toString() {
        return "Billet de " + member.getName() + " pour le concert de " + this.concert.getName();
    }
}
