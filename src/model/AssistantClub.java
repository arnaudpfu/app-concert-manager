package model;
import java.util.ArrayList;

import model.exceptions.FullRoomException;

public class AssistantClub implements IReservationListener {
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
