package view.pages;

import javax.swing.*;

import model.Club;
import model.ClubManager;
import model.Member;
import model.Room;
import view.components.BoxRadius;
import view.components.Typography;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RoomManagerPage extends InterfaceApp {
    private JPanel panel;
    private JLabel title;
    private JButton backButton;

    public RoomManagerPage(ClubManager clubManager) {
        super("Room Manager Page", clubManager);

        // create the panel and add components
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setSize(800, 800);

        backButton = new JButton("< Back to Home");
        // add action listener to the button
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // navigate back to home page
                HomePage homePage = new HomePage(clubManager);
                homePage.setVisible(true);
                dispose();
            }
        });

        this.addSpacer(panel);
        panel.add(backButton);
        this.addSpacer(panel);

        this.addSpacer(panel);
        this.addManagerClubSection(panel);
        this.addSpacer(panel);
        this.addRoomSection(panel);
        this.addSpacer(panel);
        // create a box with a border solid of 1px and with a border radius of 5px

        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // add panel to the frame and center the frame
        add(panel);

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(scrollPane, BorderLayout.EAST);
    }

    private JPanel createMemberLine(Member member) {
        JPanel memberLine = new JPanel();
        memberLine.setLayout(new BoxLayout(memberLine, BoxLayout.X_AXIS));

        memberLine.add(new Typography(member.getName(), 3));

        JButton removeButton = new JButton("Retirer");
        memberLine.add(removeButton);
        return memberLine;
    }

    private JPanel createMemberContainer(Club club) {
        JPanel membersContainer = new JPanel();
        membersContainer.setLayout(new BoxLayout(membersContainer, BoxLayout.Y_AXIS));
        membersContainer.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));

        ArrayList<Member> members = club.getMembers();
        for (int j = 0; j < members.size(); j++) {
            Member member = members.get(j);
            membersContainer.add(this.createMemberLine(member));
        }

        JPanel memberAddLine = new JPanel();
        memberAddLine.setLayout(new BoxLayout(memberAddLine, BoxLayout.X_AXIS));
        // memberLine.add(new Typography(member.getName(), 3));

        JTextField memberField = new JTextField();
        memberField.setHorizontalAlignment(JTextField.CENTER);
        memberField.setPreferredSize(new Dimension(200, 30));
        memberAddLine.add(memberField);

        JButton addButton = new JButton("Ajouter");
        memberAddLine.add(addButton);

        membersContainer.add(memberAddLine);

        return membersContainer;
    }

    private void addManagerClubSection(JPanel panel) {
        panel.add(new Typography("Gestionnaire de Club", 1));
        this.addSpacer(panel);

        BoxRadius section = new BoxRadius();

        ArrayList<Club> clubs = clubManager.getClubs();
        JPanel clubsContainer = new JPanel();
        clubsContainer.setLayout(new BoxLayout(clubsContainer, BoxLayout.Y_AXIS));
        clubsContainer.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        section.add(clubsContainer);

        for (int i = 0; i < clubs.size(); i++) {
            Club club = clubs.get(i);
            clubsContainer.add(new Typography(club.getName(), 2));
            this.addSpacer(clubsContainer);

            clubsContainer.add(this.createMemberContainer(club));
            this.addSpacer(panel);
        }

        panel.add(section);

        this.addSpacer(panel);
        panel.add(new Typography("Ajouter un Club", 1));

        JPanel clubAddLine = new JPanel();
        clubAddLine.setLayout(new BoxLayout(clubAddLine, BoxLayout.X_AXIS));

        JTextField clubField = new JTextField();
        clubField.setHorizontalAlignment(JTextField.CENTER);
        clubField.setPreferredSize(new Dimension(200, 30));
        clubAddLine.add(clubField);

        JButton addButton = new JButton("Ajouter");
        clubAddLine.add(addButton);

        panel.add(clubAddLine);
    }

    private JPanel createRoomLine(Room room, Club club) {
        JPanel memberLine = new JPanel();
        memberLine.setLayout(new BoxLayout(memberLine, BoxLayout.X_AXIS));

        memberLine.add(new Typography(room.getName(), 3));

        memberLine.add(new Typography(club == null ? "Vide" : "Salle occupé par le club " + club.getName(), 3));

        return memberLine;
    }

    private void addRoomSection(JPanel panel) {
        panel.add(new Typography("Salles", 1));
        this.addSpacer(panel);

        BoxRadius section = new BoxRadius();

        HashMap<Room, Club> rooms = clubManager.getRoomManager().getRooms();
        JPanel roomsContainer = new JPanel();
        roomsContainer.setLayout(new BoxLayout(roomsContainer, BoxLayout.Y_AXIS));
        roomsContainer.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));

        for (Map.Entry<Room, Club> set : rooms.entrySet()) {
            Room room = set.getKey();
            Club club = set.getValue();

            roomsContainer.add(this.createRoomLine(room, club));
        }

        section.add(roomsContainer);

        panel.add(section);
    }
}
