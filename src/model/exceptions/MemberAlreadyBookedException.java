package model.exceptions;

import model.Member;
import model.Ticket;

public class MemberAlreadyBookedException extends Exception {
    public MemberAlreadyBookedException(Ticket ticket, Member member) {
        super("Le membre " + member.getName() + " à déjà réservé pour " + ticket.getConcert().getName());
    }

}
