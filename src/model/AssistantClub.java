package model;
import java.util.ArrayList;

import model.exceptions.FullRoomException;

public class AssistantClub implements IConcertListener, IReservationListener {
    public void onMembersInformed(ConcertEvent event, ArrayList<Member> members) {
        for (Member m : members) {
            m.onNotifyNewConcert(event.getConcert());
        }
    }
    public void onConcertAnnulation(ConcertEvent event, ArrayList<Member> members) {
        for (Member m : members) {
            m.onNotifyConcertAnnulation(event.getConcert());
        }
    }

//    public void onReservation(ConcertEvent event, Member member) {
//        // Tests if the member has a big enough threshold
//        if(!member.canReserve(event.getConcert())) {
//            System.out.println("Prix non conforme");
//            return;
//        }
//
//        // Tests if the concert can be reserved
//        try {
//            event.getConcert().addReservation(member);
//        } catch (FullRoomException e) {
//            System.out.println("La salle est pleine");
//        }
//    }
//
//    public void onAnnulation(ConcertEvent event, Member member) {
//        event.getConcert().cancelReservation(member);
//    }

    @Override
    public void onReservation(ReservationEvent event) {
        // Tests if the member has a big enough threshold
        if(!event.getMember().canReserve(event.getConcert())) {
            System.out.println("Prix non conforme");
            return;
        }

        // Tests if the concert can be reserved
        try {
            event.getConcert().addReservation(event.getMember());
        } catch (FullRoomException e) {
            System.out.println("La salle est pleine");
        }
    }

    @Override
    public void onAnnulation(ReservationEvent event) { event.getConcert().cancelReservation(event.getMember()); }
}
