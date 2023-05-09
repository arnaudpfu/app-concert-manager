package model.exceptions;

import model.Member;
import model.Ticket;

public class AlreadyBookedException extends Exception {
    public AlreadyBookedException(Ticket ticket, Member member) {
        super("Le membre " + member.getName() + " à déjà réservé pour " + ticket.getConcert().getName());
    }

}
