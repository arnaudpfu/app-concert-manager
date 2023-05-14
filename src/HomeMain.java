import model.*;
import view.pages.HomePage;
import view.pages.InterfaceApp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

class HomeMain {
    public static void main(String[] args) {
        Room c201 = new Room("c201", 2);
        Room c205 = new Room("c205", 2);
        Room c212 = new Room("c212", 2);

        ClubManager clubManager = new ClubManager(new ArrayList<>(Arrays.asList(c201, c205, c212)));
        RoomManager roomManager = clubManager.getRoomManager();

        Member m1 = new Member("Jean", 10);
        Member m2 = new Member("Paul", 90);
        Member m3 = new Member("Jacques", 30);

        Club club = new Club("azer", new ArrayList<>(Arrays.asList(m1, m2, m3)));
        club.addConcertListener(roomManager);
        clubManager.addClub(club);

        Concert c1, c2;
        try {
            Date c1_date = new Date(123, Calendar.FEBRUARY,1);
            c1 = new Concert("Metallica", c201, 50, c1_date);
            club.addConcert(c1);

            Date c2_date = new Date(123, Calendar.FEBRUARY,2);
            c2 = new Concert("AC/DC", c205, 1, c2_date);
            club.addConcert(c2);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return;
        }

//        try {
//            clubManager.attemptReservation(m1, c1);
//            clubManager.attemptReservation(m1, c2);
//            clubManager.attemptReservation(m2, c1);
//        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
//        }
//
//        System.out.println("\n" + m1.ticketsToString());
//        System.out.println(m2.ticketsToString());

        InterfaceApp window = new HomePage(clubManager);
        window.setVisible(true);
    }
}
