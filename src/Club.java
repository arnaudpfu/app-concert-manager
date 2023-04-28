import java.util.ArrayList;

public class Club {
    private ArrayList<ConcertListener> re;
    private ArrayList<Membre> membres;
    private ArrayList<Concert> concerts;

    public Club() {
        this.re = new ArrayList<ConcertListener>();
        this.membres = new ArrayList<Membre>();
        this.concerts = new ArrayList<Concert>();
    }

    public Club(ArrayList<Membre> membres) {
        this.membres = membres;
        this.re = new ArrayList<ConcertListener>();
        this.concerts = new ArrayList<Concert>();
    }

    public Club(ArrayList<ConcertListener> assistants, ArrayList<Membre> membres) {
        this.membres = membres;
        this.re = assistants;
        this.concerts = new ArrayList<Concert>();
    }

    public void addMembre(Membre m) {
        this.membres.add(m);
    }

    public void addConcert(Concert c) {
        this.concerts.add(c);
    }

    private void addConcertListener(ConcertListener ml) {
        this.re.add(ml);
    }

    private void removeConcertListener(ConcertListener ml) {
        this.re.remove(ml);
    }

    public void addAssistant(ConcertListener ml) {
        this.re.add(ml);
        this.addConcertListener(ml);
    }

    public void removeAssistant(ConcertListener ml) {
        this.re.remove(ml);
        this.removeConcertListener(ml);
    }

    private void dispatchInformation(Concert c) {
        ConcertEvent event = new ConcertEvent(this, c);
        for (ConcertListener l : re) {
            l.onMembersInformed(event, membres);
        }
    }

    private void dispatchAnnulation(Concert c, Membre m) {
        ConcertEvent event = new ConcertEvent(this, c);
        for (ConcertListener l : re) {
            l.onAnnulation(event, m);
        }
    }

    private void dispatchReservation(Concert c, Membre m) {
        ConcertEvent event = new ConcertEvent(this, c);
        for (ConcertListener l : re) {
            l.onReservation(event, m);
        }
    }

    public void informMembers(Concert c) {
        if (concerts.contains(c)) {
            dispatchInformation(c);
        }
    }

    public void cancelMemberReservation(Concert c, Membre m) {
        if (concerts.contains(c) && membres.contains(m)) {
            dispatchAnnulation(c, m);
        }
    }

    public void reserverBillet(Concert c, Membre m, String nomSalle) {
        if (c.getNomSalle() != nomSalle) {
            System.out.println("Nom de salle différent");
            return;
        }

        if (!concerts.contains(c) || !membres.contains(m)) {
            System.out.println("Concert ou membre non enregistré");
            return;
        }

        dispatchReservation(c, m);
    }
}
