package model;

import java.util.ArrayList;

public interface ConcertListener {
    void onMembersInformed(ConcertEvent concert, ArrayList<Member> members);
    void onConcertAnnulation(ConcertEvent concert, ArrayList<Member> members);
    void onReservation(ConcertEvent concert, Member member);

    void onAnnulation(ConcertEvent concert, Member member);

}
