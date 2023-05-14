package model.exceptions;

import model.Concert;
import model.Member;

public class UnknownTicketException extends UnknownError {

    public UnknownTicketException(Concert concert, Member member) {
        super(member.getName() + " n'a jamais réservé de ticket pour " + concert.getName());
    }

}
