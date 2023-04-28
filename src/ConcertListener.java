import java.util.ArrayList;

public interface ConcertListener {
    public void onMembersInformed(ConcertEvent concert, ArrayList<Membre> membres);

    public void onReservation(ConcertEvent concert, Membre membre);

    public void onAnnulation(ConcertEvent concert, Membre membre);
}
