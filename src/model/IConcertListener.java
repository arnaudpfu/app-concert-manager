package model;

import java.util.ArrayList;

public interface IConcertListener {
    void onMembersInformed(ConcertEvent concert, ArrayList<Member> members);
    void onConcertAnnulation(ConcertEvent concert, ArrayList<Member> members);
}
