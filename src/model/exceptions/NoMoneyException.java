package model.exceptions;

import model.Concert;
import model.Member;
import model.Ticket;

public class NoMoneyException extends Exception {
    public NoMoneyException(Member member, Concert concert) {
        super("Le membre " + member.getName() + " a un seuil insuffisant pour réserver " + concert.getName());
    }

}
