
import java.util.ArrayList;
import java.util.Arrays;

import controller.ClubManager;
import model.Club;
import model.Concert;
import model.Membre;

class App {
    public static void main(String[] args) {
        Membre m1 = new Membre("Jean", 10);
        Membre m2 = new Membre("Paul", 20);
        Membre m3 = new Membre("Jacques", 30);

        Concert c1 = new Concert("Metallica", "1", 100, 12);
        Concert c2 = new Concert("AC/DC", "2", 1, 2);

        ClubManager a1 = new ClubManager();

        Club clubMusic = new Club(a1, new ArrayList<Membre>(Arrays.asList(m1, m2)));

        clubMusic.addConcert(c1);
        clubMusic.informMembers(c1);

        clubMusic.reserverBillet(c1, m1, "1");
        clubMusic.reserverBillet(c1, m2, "1");

        System.out.println("\n" + m1.billetsToString());
        System.out.println(m2.billetsToString());

        // try to trigger exception
        clubMusic.addConcert(c2);
        clubMusic.reserverBillet(c2, m1, "2");
        clubMusic.reserverBillet(c2, m2, "2");

    }
}
