package model;
import java.util.ArrayList;

import model.exceptions.FullRoomException;

public class AssistantClub implements ConcertListener {
    public void onMembersInformed(ConcertEvent event, ArrayList<Member> members) {
        for (Member m : members) {
            if (m.getPriceThreshold() >= event.getConcert().getTicketPrice()) {
                System.out.println(
                        m.getName() + " est intéressé par " + event.getConcert().getName() + ".");
            } else {
                System.out.println(m.getName() + " trouve ce concert trop cher !");
            }
        }
    }

    public void onReservation(ConcertEvent event, Member member) {
        // Tests if the member has a big enough threshold
        if(!member.canReserve(event.getConcert())) {
            System.out.println("Prix non conforme");
            return;
        }

        // Tests if the concert can be reserved
        try {
            event.getConcert().addReservation(member);
        } catch (FullRoomException e) {
            System.out.println("La salle est pleine");
        }
    }

    public void onAnnulation(ConcertEvent event, Member member) {
        event.getConcert().cancelReservation(member);
    }
}
