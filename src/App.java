
import java.util.ArrayList;
import java.util.Arrays;

import model.Club;
import model.ClubManager;
import model.Concert;
import model.Member;
import view.pages.HomePage;
import view.pages.InterfaceApp;

class App {
    public static void main(String[] args) {
        ClubManager clubManager = new ClubManager();

        Member m1 = new Member("Jean", 10);
        Member m2 = new Member("Paul", 20);
        Member m3 = new Member("Jacques", 30);

        Concert c1 = new Concert("Metallica", "1", 100, 12);
        Concert c2 = new Concert("AC/DC", "2", 1, 2);

        Club clubMusic = new Club("Les métalleux", clubManager, new ArrayList<Member>(Arrays.asList(m1, m2)));

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

        InterfaceApp window = new HomePage(clubManager);
        window.setVisible(true);
    }
}
