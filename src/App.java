
import java.util.ArrayList;
import java.util.Arrays;

import controller.ClubManager;
import model.Club;
import model.Concert;
import model.Member;

class App {
    public static void main(String[] args) {
        Member m1 = new Member("Jean", 10);
        Member m2 = new Member("Paul", 20);
        Member m3 = new Member("Jacques", 30);

        Concert c1 = new Concert("Metallica", "1", 100, 12);
        Concert c2 = new Concert("AC/DC", "2", 1, 2);

        ClubManager a1 = new ClubManager();

        Club clubMusic = new Club("Les métalleux",a1, new ArrayList<Member>(Arrays.asList(m1, m2)));

        clubMusic.addConcert(c1);
        clubMusic.informMembers(c1);

        clubMusic.reserverBillet(c1, m1, "1");
        clubMusic.reserverBillet(c1, m2, "1");

        System.out.println("\n" + m1.ticketsToString());
        System.out.println(m2.ticketsToString());

        // try to trigger exception
        clubMusic.addConcert(c2);
        clubMusic.reserverBillet(c2, m1, "2");
        clubMusic.reserverBillet(c2, m2, "2");

    }
}
