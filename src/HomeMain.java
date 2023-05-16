import model.*;
import view.pages.HomePage;
import view.pages.InterfaceApp;

import java.util.*;

class HomeMain {
    public static void main(String[] args) {
        Room c201 = new Room("c201", 2);
        Room c205 = new Room("c205", 2);
        Room c212 = new Room("c212", 2);

        RoomManager roomManager = new RoomManager(new ArrayList<>(Arrays.asList(c201, c205, c212)));

        Member m1 = new Member("Jean", 10);
        Member m2 = new Member("Paul", 90);
        Member m3 = new Member("Jacques", 30);

        Club club = new Club("azer", new ArrayList<>(Arrays.asList(m1, m2, m3)));

        // Add all listeners
        // PS : Members are automatically added in their own listeners list
        // PS : Members are automatically added in the `club` listeners list
        m1.addTicketsListener(new ArrayList<>(Arrays.asList(m2, m3, club, roomManager)));
        m2.addTicketsListener(new ArrayList<>(Arrays.asList(m1, m3, club, roomManager)));
        m3.addTicketsListener(new ArrayList<>(Arrays.asList(m1, m2, club, roomManager)));
        club.addConcertListener(roomManager);

        Concert c1, c2, c3;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        try {
            // First concert, today
            c1 = new Concert("Metallica", c201, 100, calendar.getTime());
            club.addConcert(c1);

            // Second concert, one month ago
            calendar.add(Calendar.MONTH, -1);
            c2 = new Concert("AC/DC", c205, 20, calendar.getTime());
            club.addConcert(c2);

            // Third concert, two month later
            calendar.add(Calendar.MONTH, 3);
            c3 = new Concert("Psychotrope", c205, 55, calendar.getTime());
            club.addConcert(c3);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return;
        }

        try {
            m1.book(c1);
            m1.book(c2);
            m2.book(c1);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return;
        }

        InterfaceApp window = new HomePage(
            new ArrayList<>(List.of(club)),
            new ArrayList<>(Arrays.asList(m1,m2,m3)),
            roomManager
        );
        window.setVisible(true);
    }
}
