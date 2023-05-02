
import java.util.ArrayList;
import java.util.Arrays;

import model.Club;
import model.ClubManager;
import model.Concert;
import model.Member;
import model.Room;
import view.pages.HomePage;
import view.pages.InterfaceApp;

class App {
    public static void main(String[] args) {
        Room c201 = new Room("c201", 200);
        Room c205 = new Room("c205", 180);
        Room c212 = new Room("c212", 130);

        ClubManager clubManager = new ClubManager(new ArrayList<Room>(Arrays.asList(c201, c205, c212)));

        Member m1 = new Member("Jean", 10);
        Member m2 = new Member("Paul", 100);
        Member m3 = new Member("Jacques", 30);

        Concert c1 = new Concert("Metallica", c201, 100);
        Concert c2 = new Concert("AC/DC", c205, 1);
        Concert c3 = new Concert("Dreadknogths", c212, 85);

        // Club clubMusic = new Club("Les métalleux", clubManager, new
        // ArrayList<Member>(Arrays.asList(m1, m2, m3)));
        Club clubMusic = new Club("azer", clubManager, new ArrayList<Member>(Arrays.asList(m1, m2, m3)));

        clubMusic.addConcert(c1);
        clubMusic.informMembers(c1);
        clubMusic.addConcert(c3);
        clubMusic.informMembers(c3);

        clubMusic.reserverBillet(c1, m1, "1");
        clubMusic.reserverBillet(c1, m2, "1");
        clubMusic.reserverBillet(c3, m2, "3");

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
