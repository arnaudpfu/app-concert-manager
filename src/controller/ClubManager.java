package controller;
import java.util.ArrayList;

import model.Member;
import model.exceptions.SallePleineException;

public class ClubManager implements ConcertListener {
    public void onMembersInformed(ConcertEvent event, ArrayList<Member> membres) {
        for (Member m : membres) {
            if (m.getPrixSeuil() >= event.getConcert().getPrixBillet()) {
                System.out.println(
                        m.getName() + " est intéressé par " + event.getConcert().getNom() + ".");
            } else {
                System.out.println(m.getName() + " trouve ce concert trop cher !");
            }
        }
    }

    public void onReservation(ConcertEvent event, Member membre) {
        if (membre.getPrixSeuil() >= event.getConcert().getPrixBillet()) {
            try {
                event.getConcert().addReservation(membre);
            } catch (SallePleineException e) {
                System.out.println("La salle est pleine");
            }
        }else{
            System.out.println( "Prix non conforme" );
        }
    }

    public void onAnnulation(ConcertEvent event, Member membre) {
        event.getConcert().cancelReservation(membre);
    }
}
