package model;

import java.util.ArrayList;

public interface ConcertListener {
    void onMembersInformed(ConcertEvent concert, ArrayList<Member> membres);

    void onReservation(ConcertEvent concert, Member membre);

    void onAnnulation(ConcertEvent concert, Member membre);
}
