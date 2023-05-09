
import java.util.ArrayList;
import java.util.Arrays;

import model.Club;
import model.ClubManager;
import model.Concert;
import model.Member;
import model.Room;
import model.exceptions.FullRoomException;
import view.pages.HomePage;
import view.pages.InterfaceApp;

class HomeMain {
    public static void main(String[] args) {
        Room c201 = new Room("c201", 200);
        Room c205 = new Room("c205", 180);
        Room c212 = new Room("c212", 130);

        ClubManager clubManager = new ClubManager(new ArrayList<>(Arrays.asList(c201, c205, c212)));

        Member m1 = new Member("Jean", 10);
        Member m2 = new Member("Paul", 100);
        Member m3 = new Member("Jacques", 30);

        Concert c1 = new Concert("Metallica", c201, 100);
        Concert c2 = new Concert("AC/DC", c205, 1);
        Concert c3 = new Concert("Dreadknogths", c212, 85);

        // Club clubMusic = new Club("Les métalleux", clubManager, new
        // ArrayList<Member>(Arrays.asList(m1, m2, m3)));
        Club clubMusic = new Club("azer", clubManager, new ArrayList<>(Arrays.asList(m1, m2, m3)));

        clubMusic.addConcert(c1);
        clubMusic.addConcert(c2);
        clubMusic.addConcert(c3);

        try {
            clubManager.attemptReservation(m1, c1);
            clubManager.attemptReservation(m2, c1);
            clubManager.attemptReservation(m2, c3);
            clubManager.attemptReservation(m1, c2);
            clubManager.attemptReservation(m2, c2);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        System.out.println("\n" + m1.ticketsToString());
        System.out.println(m2.ticketsToString());

        InterfaceApp window = new HomePage(clubManager);
        window.setVisible(true);
    }
}
