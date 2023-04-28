package controller;
import java.util.ArrayList;

import model.Member;

public interface ConcertListener {
    public void onMembersInformed(ConcertEvent concert, ArrayList<Member> membres);

    public void onReservation(ConcertEvent concert, Member membre);

    public void onAnnulation(ConcertEvent concert, Member membre);
}
