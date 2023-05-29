package view.pages;

import javax.swing.*;

import model.*;
import view.components.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

public class RoomManagerPage extends InterfaceApp implements ActionListener {
    private BackButtonPanel backButtonPanel;
    private JPanel roomsPanel = new DefaultPanel();
    private JPanel clubsPanel = new DefaultPanel();
    private JLabel clubErrorsLabel = new DefaultErrorLabel();
    private RoomManager roomManager;
    private ArrayList<Club> clubs;
    private ArrayList<Member> members;

    public RoomManagerPage(ArrayList<Club> clubs, ArrayList<Member> members, RoomManager roomManager) {
        super("Gestionnaire de salle(s)", clubs, members, roomManager);
        this.roomManager = roomManager;
        this.clubs = clubs;
        this.members = members;
        roomManager.setWindow(this);

        backButtonPanel = new BackButtonPanel("< Retour à l'accueil", this);
        mainPanel.add(backButtonPanel);

        // Club managing panel
        mainPanel.add(new TitleLabel("Gestionnaire de club"));
        addClubsPanel();

        // Club creation panel
        addClubCreationPanel();
        mainPanel.add(clubErrorsLabel);

        // Room managing panel
        addRoomsPanel();

        // Making the window scrollable
        endFrameCreation();
    }

    /** Redraws the clubs panel with the new clubs data **/
    public void updateClubs() {
        clubsPanel.removeAll();
        for (Club club : clubs) {
            clubsPanel.add(new TitleLabel(club.getName()));
            clubsPanel.add(this.createClubPanel(club));
        }
        clubsPanel.repaint();
        clubsPanel.revalidate();
    }

    /** Redraws the rooms panel with the new rooms data **/
    public void updateRooms() {
        roomsPanel.removeAll();
        for (Room room : roomManager.getRooms()) {
            roomsPanel.add(new RoomLine(room));
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
        section.add(clubsPanel);
        updateClubs();
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
            clubErrorsLabel.setText("");
            String clubName = clubField.getText().toLowerCase();

            // Validates inputs
            if (clubName.isEmpty()) {
                clubErrorsLabel.setText("Veuillez renseigner le nom du club");
                return;
            }

            // Check if club already exists
            boolean club_exists = clubs.stream().map(Club::getName).anyMatch(name -> Objects.equals(name, clubName));
            if(club_exists) {
                clubErrorsLabel.setText("Ce club existe déjà");
                return;
            }

            Club club = new Club(clubName);
            clubs.add(club);
            club.addConcertListener(roomManager);

            // Update the clubs panel
            updateClubs();
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
                // TODO : Add IMemberListener
                club.removeMember(member);
                updateClubs();
            });
            membersContainer.add(memberLine);
        }

        JPanel memberAddLine = new JPanel();
        memberAddLine.setOpaque(false);
        memberAddLine.setLayout(new BoxLayout(memberAddLine, BoxLayout.X_AXIS));

        memberAddLine.add(new DefaultLabel("Nom : "));
        JTextField memberField = new DefaultTextField();
        memberAddLine.add(memberField);
        memberAddLine.add(Box.createRigidArea(new Dimension(50, 20)));

        memberAddLine.add(new DefaultLabel("Soldes : "));
        JTextField priceField = new DefaultTextField();
        memberAddLine.add(priceField);

        JButton addMemberButton = new PrimaryButton("Ajouter");

        JLabel errorMemberLabel = new DefaultErrorLabel();

        addMemberButton.addActionListener(e -> {
            String newMemberName = memberField.getText().toLowerCase();
            String newMemberPrice = priceField.getText().toLowerCase();

            // Validate inputs
            String errorMessage = "";
            if (newMemberName.isEmpty()) {
                errorMessage += "Veuillez renseigner le nom du membre.\n";
            }
            if (newMemberPrice.isEmpty()) {
                errorMessage += "Veuillez renseigner le solde du membre.";
            }
            if (!errorMessage.isEmpty()) {
                showErrorMessage(errorMessage);
                return;
            }

            club.addMember(new Member(newMemberName, Double.parseDouble(newMemberPrice)));

            updateClubs();
        });
        memberAddLine.add(addMemberButton);

        membersContainer.add(memberAddLine);
        membersContainer.add(errorMemberLabel);

        return membersContainer;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == backButtonPanel.getBackButton()) {
            new HomePage(clubs, members, roomManager);
            dispose();
        }
    }
}
