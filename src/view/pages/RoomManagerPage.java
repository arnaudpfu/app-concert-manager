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
    private JButton backButton;

    public RoomManagerPage(ClubManager clubManager) {
        super("Room Manager Page", clubManager);

        // create the panel and add components
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        // panel.setSize(InterfaceApp.WIDTH, InterfaceApp.HEIGHT);

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
        this.getContentPane().add(panel);

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(scrollPane, BorderLayout.EAST);
    }

    private JPanel createMemberLine(Club club, Member member) {
        JPanel memberLine = new JPanel();
        memberLine.setLayout(new FlowLayout());

        memberLine.add(new Typography(member.getName(), 3));
        memberLine.add(new Typography(member.getPriceThreshold() + " €", 3));

        JButton removeButton = new JButton("Retirer");
        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                club.removeMember(member);
                refreshPage();
            }
        });
        memberLine.add(removeButton);
        return memberLine;
    }

    private void refreshPage() {
        RoomManagerPage roomManagerPage = new RoomManagerPage(clubManager);
        roomManagerPage.setVisible(true);
        this.dispose();
    }

    private JPanel createMemberContainer(Club club) {
        JPanel membersContainer = new JPanel();
        membersContainer.setLayout(new BoxLayout(membersContainer, BoxLayout.Y_AXIS));
        membersContainer.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));

        ArrayList<Member> members = club.getMembers();
        for (Member member : members) {
            membersContainer.add(this.createMemberLine(club, member));
        }

        JPanel memberAddLine = new JPanel();
        memberAddLine.setLayout(new BoxLayout(memberAddLine, BoxLayout.X_AXIS));

        memberAddLine.add(new Typography("Nom : ", 3));
        JTextField memberField = new JTextField();
        memberField.setHorizontalAlignment(JTextField.LEFT);
        memberField.setPreferredSize(new Dimension(120, 30));
        memberAddLine.add(memberField);

        memberAddLine.add(new Typography("Prix seuil : ", 3));
        JTextField priceField = new JTextField();
        priceField.setHorizontalAlignment(JTextField.LEFT);
        priceField.setPreferredSize(new Dimension(120, 30));
        memberAddLine.add(priceField);

        JButton addButton = new JButton("Ajouter");

        // TODO : Delegate to a controller
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String newMemberName = memberField.getText().toLowerCase();
                String newMemberPrice = priceField.getText().toLowerCase();

                // Validate inputs
                String errorMessage = "";
                if(newMemberName.isEmpty()) {
                    errorMessage += "Veuillez renseigner le nom du membre.\n";
                }
                if (newMemberPrice.isEmpty()) {
                    errorMessage += "Veuillez renseigner le seuil de prix du membre.";
                }
                if(!errorMessage.isEmpty()) {
                    showErrorMessage(errorMessage);
                    return;
                }

                club.addMember(new Member(newMemberName, Double.parseDouble(newMemberPrice)));

                refreshPage();
            }
        });
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

        for (Club club : clubs) {
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

        // TODO : Delegate to a controller
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String newClubName = clubField.getText().toLowerCase();
                if(newClubName.isEmpty()) {
                    showErrorMessage("Veuillez renseigner le nom du club.");
                    return;
                }
                clubManager.addClubByName(newClubName);

                refreshPage();
            }
        });
        clubAddLine.add(addButton);

        panel.add(clubAddLine);
    }

    private JPanel createRoomLine(Room room, Club club) {
        JPanel memberLine = new JPanel();
        memberLine.setLayout(new BoxLayout(memberLine, BoxLayout.X_AXIS));

        memberLine.add(new Typography(room.getName(), 3));

        memberLine.add(Box.createRigidArea(new Dimension(100, 20)));

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
