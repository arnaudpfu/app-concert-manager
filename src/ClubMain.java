import model.*;
import view.pages.ClubPage;
import view.pages.InterfaceApp;

import java.util.ArrayList;
import java.util.Arrays;

class ClubMain {
    public static void main(String[] args) {
        Room c201 = new Room("c201", 200);
        Room c205 = new Room("c205", 180);
        Room c212 = new Room("c212", 130);

        ClubManager clubManager = new ClubManager(new ArrayList<>(Arrays.asList(c201, c205, c212)));

        Member m1 = new Member("Jean", 10);
        Member m2 = new Member("Paul", 100);
        Member m3 = new Member("Jacques", 30);


        Club clubMusic = new Club("azer", clubManager, new ArrayList<>(Arrays.asList(m1, m2, m3)));

        Concert c1, c2;
        try {
            clubManager.getRoomManager().attemptRoomReservation(c201, clubMusic);
            c1 = new Concert("Metallica", c201, 100);
            clubMusic.addConcert(c1);
            clubManager.getRoomManager().attemptRoomReservation(c205, clubMusic);
            c2 = new Concert("AC/DC", c205, 1);
            clubMusic.addConcert(c2);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return;
        }

        try {
            clubManager.attemptReservation(m1, c1);
            clubManager.attemptReservation(m1, c2);
            clubManager.attemptReservation(m2, c1);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        System.out.println("\n" + m1.ticketsToString());
        System.out.println(m2.ticketsToString());

        InterfaceApp window = new ClubPage(clubManager, clubMusic);
        window.setVisible(true);
    }
}
