package view.pages;

import javax.swing.*;

import model.*;
import view.components.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class RoomManagerPage extends InterfaceApp implements ActionListener {
    private BackButtonPanel backButtonPanel;
    private JPanel roomsPanel = new JPanel();
    private JPanel clubsPanel = new JPanel();
    public RoomManagerPage(ClubManager clubManager) {
        super("Gestionnaire de salle(s)", clubManager);
        clubManager.getRoomManager().setWindow(this);

        backButtonPanel = new BackButtonPanel("< Retour à l'accueil", this);
        mainPanel.add(backButtonPanel);

        // Club managing panel
        mainPanel.add(new TitleLabel("Gestionnaire de club"));
        addClubsPanel();

        // Club creation panel
        addClubCreationPanel();

        // Room managing panel
        addRoomsPanel();

        // Making the window scrollable
        endFrameCreation();
    }

    /** Redraws the clubs panel with the new clubs data **/
    public void updateClubsPanel() {
        clubsPanel.removeAll();
        for (Club club : clubManager.getClubs()) {
            clubsPanel.add(new TitleLabel(club.getName()));
            clubsPanel.add(this.createClubPanel(club));
        }
        clubsPanel.repaint();
        clubsPanel.revalidate();
    }

    /** Redraws the rooms panel with the new rooms data **/
    public void updateRooms() {
        roomsPanel.removeAll();
        for (Map.Entry<Room, ArrayList<Date>> set : clubManager.getRoomManager().getRooms().entrySet()) {
            Room room = set.getKey();
            ArrayList<Date> dates = set.getValue();
            roomsPanel.add(new RoomLine(room, dates));
        }
        roomsPanel.repaint();
        roomsPanel.revalidate();
    }

    /** Adds the clubs panel (list of clubs and it's members) on the page **/
    public void addClubsPanel() {
        BoxRadius section = new BoxRadius(new Color(229, 229, 229));
        clubsPanel.setOpaque(false);
        clubsPanel.setLayout(new BoxLayout(clubsPanel, BoxLayout.Y_AXIS));
        clubsPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        updateClubsPanel();
        section.add(clubsPanel);
        updateClubsPanel();
        mainPanel.add(section);
    }

    /** Adds the rooms panel (list of clubs and it's members) on the page **/
    private void addRoomsPanel() {
        mainPanel.add(new TitleLabel("Salles"));
        BoxRadius section = new BoxRadius(new Color(229, 229, 229));

        roomsPanel.setOpaque(false);
        roomsPanel.setLayout(new BoxLayout(roomsPanel, BoxLayout.Y_AXIS));
        roomsPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        updateRooms();
        section.add(roomsPanel);
        mainPanel.add(section);
    }

    /** Adds the club creation form (club name + submit button)  on the page **/
    public void addClubCreationPanel() {
        mainPanel.add(new TitleLabel("Ajouter un Club"));
        JPanel clubAddLine = new JPanel();
        clubAddLine.setOpaque(false);
        clubAddLine.setLayout(new BoxLayout(clubAddLine, BoxLayout.X_AXIS));
        JTextField clubField = new DefaultTextField();
        clubAddLine.add(clubField);
        JButton addButton = new PrimaryButton("Ajouter");
        addButton.addActionListener(e -> {
            String newClubName = clubField.getText().toLowerCase();
            if (newClubName.isEmpty()) {
                showErrorMessage("Veuillez renseigner le nom du club.");
                return;
            }
            clubManager.addClubByName(newClubName);
            updateClubsPanel();
        });
        clubAddLine.add(addButton);
        mainPanel.add(clubAddLine);
    }

    /** Returns a club panel (list of all members (name + threshold + remove button)) **/
    private JPanel createClubPanel(Club club) {
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

        JButton addMemberButton = new PrimaryButton("Ajouter");
        addMemberButton.addActionListener(e -> {
            String newMemberName = memberField.getText().toLowerCase();
            String newMemberPrice = priceField.getText().toLowerCase();

            // Validate inputs
            String errorMessage = "";
            if (newMemberName.isEmpty()) {
                errorMessage += "Veuillez renseigner le nom du membre.\n";
            }
            if (newMemberPrice.isEmpty()) {
                errorMessage += "Veuillez renseigner le seuil de prix du membre.";
            }
            if (!errorMessage.isEmpty()) {
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

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == backButtonPanel.getBackButton()) {
            new HomePage(clubManager);
            dispose();
        }
    }
}
