import model.*;
import view.pages.ClubPage;
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

        try {
            m1.book(c1);
            m1.book(c2);
            m2.book(c1);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        InterfaceApp window = new ClubPage(
            new ArrayList<>(List.of(club)),
            new ArrayList<>(Arrays.asList(m1,m2,m3)),
            roomManager,
            club
        );
        window.setVisible(true);
    }
}
