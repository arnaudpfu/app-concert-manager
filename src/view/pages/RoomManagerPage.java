package view.pages;

import javax.swing.*;

import model.Club;
import model.ClubManager;
import model.Member;
import model.Room;
import view.components.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RoomManagerPage extends InterfaceApp implements ActionListener {
    private JPanel panel;
    private BackButtonPanel backButtonPanel = new BackButtonPanel("< Retour à l'accueil");

    private JPanel clubsPanel = new JPanel();
    public RoomManagerPage(ClubManager clubManager) {
        super("Room Manager Page", clubManager);

        panel = new MainPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        backButtonPanel.getBackButton().addActionListener(this);
        panel.add(backButtonPanel);

        // Club managing panel
        panel.add(new TitleLabel("Gestionnaire de club"));
        addClubsPanel();

        // Club creation panel
        addClubCreationPanel();

        // Room managing panel
        addRoomSection(panel);

        // Make window scrollable
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(scrollPane);

    }
    public void addClubsPanel() {
        BoxRadius section = new BoxRadius(new Color(229, 229, 229));
        clubsPanel.setOpaque(false);
        clubsPanel.setLayout(new BoxLayout(clubsPanel, BoxLayout.Y_AXIS));
        clubsPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        updateClubsPanel();
        section.add(clubsPanel);
        updateClubsPanel();
        panel.add(section);
    }

    public void updateClubsPanel() {
        clubsPanel.removeAll();
        for (Club club : clubManager.getClubs()) {
            clubsPanel.add(new TitleLabel(club.getName()));
            clubsPanel.add(this.createMemberContainer(club));
        }
        clubsPanel.repaint();
        clubsPanel.revalidate();
    }

    public void addClubCreationPanel() {
        panel.add(new TitleLabel("Ajouter un Club"));
        JPanel clubAddLine = new JPanel();
        clubAddLine.setOpaque(false);
        clubAddLine.setLayout(new BoxLayout(clubAddLine, BoxLayout.X_AXIS));
        JTextField clubField = new DefaultTextField();
        clubAddLine.add(clubField);
        JButton addButton = new PrimaryButton("Ajouter");
        // TODO : Delegate to a controller
        addButton.addActionListener(e -> {
            String newClubName = clubField.getText().toLowerCase();
            if(newClubName.isEmpty()) {
                showErrorMessage("Veuillez renseigner le nom du club.");
                return;
            }
            clubManager.addClubByName(newClubName);
            updateClubsPanel();
        });
        clubAddLine.add(addButton);
        panel.add(clubAddLine);
    }

    private JPanel createMemberContainer(Club club) {
        JPanel membersContainer = new JPanel();
        membersContainer.setOpaque(false);
        membersContainer.setLayout(new BoxLayout(membersContainer, BoxLayout.Y_AXIS));

        ArrayList<Member> members = club.getMembers();
        for (Member member : members) {
            MemberLine memberLine = new MemberLine(member);
            memberLine.getRemoveButton().addActionListener(e -> {
                club.removeMember(member);
                updateClubsPanel();
            });
            membersContainer.add(memberLine);
        }

        JPanel memberAddLine = new JPanel();
        memberAddLine.setOpaque(false);
        memberAddLine.setLayout(new BoxLayout(memberAddLine, BoxLayout.X_AXIS));

        memberAddLine.add(new DefaultLabel("Nom : "));
        JTextField memberField = new DefaultTextField();
        memberAddLine.add(memberField);

        memberAddLine.add(new DefaultLabel("Prix seuil : "));
        JTextField priceField = new DefaultTextField();
        memberAddLine.add(priceField);

        // TODO : Delegate to a controller
        JButton addMemberButton = new PrimaryButton("Ajouter");
        addMemberButton.addActionListener(e -> {
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

            updateClubsPanel();
        });
        memberAddLine.add(addMemberButton);

        membersContainer.add(memberAddLine);

        return membersContainer;
    }

    private JPanel createRoomLine(Room room, Club club) {
        JPanel memberLine = new JPanel();
        memberLine.setLayout(new BoxLayout(memberLine, BoxLayout.X_AXIS));
        memberLine.setOpaque(false);

        memberLine.add(new DefaultLabel(room.getName()));

        memberLine.add(Box.createRigidArea(new Dimension(100, 20)));

        memberLine.add(new DefaultLabel(club == null ? "Vide" : "Salle occupé par le club " + club.getName()));

        return memberLine;
    }

    private void addRoomSection(JPanel panel) {
        panel.add(new TitleLabel("Salles"));
        this.addSpacer(panel);

        BoxRadius section = new BoxRadius(new Color(229, 229, 229));

        HashMap<Room, Club> rooms = clubManager.getRoomManager().getRooms();
        JPanel roomsContainer = new JPanel();
        roomsContainer.setOpaque(false);
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

    @Override
    public void actionPerformed(ActionEvent event) {
        if(event.getSource() == backButtonPanel.getBackButton()) {
            new HomePage(clubManager);
            dispose();
        }
    }
}
