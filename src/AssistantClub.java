import java.util.ArrayList;

public class AssistantClub implements ConcertListener {
    public void onMembersInformed(ConcertEvent event, ArrayList<Membre> membres) {
        for (Membre m : membres) {
            if (m.getPrixSeuil() >= event.getConcert().getPrixBillet()) {
                System.out.println(
                        m.getPrenom() + " est intéressé par " + event.getConcert().getNom() + ".");
            } else {
                System.out.println(m.getPrenom() + " trouve ce concert trop cher !");
            }
        }
    }

    public void onReservation(ConcertEvent event, Membre membre) {
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

    public void onAnnulation(ConcertEvent event, Membre membre) {
        event.getConcert().cancelReservation(membre);
    }
}
