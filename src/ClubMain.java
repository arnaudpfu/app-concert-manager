import model.*;
import view.pages.ClubPage;
import view.pages.InterfaceApp;
import view.pages.MemberPage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class ClubMain {
    public static void main(String[] args) {
        Room c201 = new Room("c201", 200);
        Room c205 = new Room("c205", 180);
        Room c212 = new Room("c212", 130);

        ClubManager clubManager = new ClubManager(new ArrayList<>(Arrays.asList(c201, c205, c212)));

        Member m2 = new Member("Paul", 100);

        Concert c1 = new Concert("Metallica", c201, 100);
        Concert c2 = new Concert("AC/DC", c205, 1);
        Concert c3 = new Concert("Dreadknogths", c212, 85);

        Club clubMusic = new Club("azer", clubManager, new ArrayList<>(List.of(m2)));

        clubMusic.addConcert(c1);
        clubMusic.informMembers(c1);
        clubMusic.addConcert(c3);
        clubMusic.informMembers(c3);
        clubMusic.addConcert(c2);

        clubMusic.createTicket(c1, m2, "c201");
        clubMusic.createTicket(c3, m2, "c212");


        InterfaceApp window = new ClubPage(clubManager, clubMusic);
        window.setVisible(true);
    }
}
