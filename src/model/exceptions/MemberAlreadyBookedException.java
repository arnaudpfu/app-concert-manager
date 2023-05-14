package model.exceptions;

import model.Concert;
import model.Member;

public class MemberAlreadyBookedException extends Exception {
    public MemberAlreadyBookedException(Concert concert, Member member) {
        super("Le membre " + member.getName() + " à déjà réservé pour " + concert.getName());
    }

}
