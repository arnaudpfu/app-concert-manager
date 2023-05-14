package view.pages;

import model.Club;
import model.Member;
import model.RoomManager;
import model.exceptions.UnknownClubException;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Objects;

public class ConnexionPageClub extends ConnexionPage {
    private ArrayList<Club> clubs;
    public ConnexionPageClub(ArrayList<Club> clubs, ArrayList<Member> members, RoomManager roomManager) {
        super(clubs, members, roomManager);
        this.clubs = clubs;
        title.setText("Connexion - Club");
        connectButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        super.actionPerformed(event);
        Object src = event.getSource();
        if(src == connectButton) {
            String textInput = nameInput.getText().toLowerCase();

            // Validates input
            if (textInput.isEmpty()) {
                errorLabel.setText("Veuillez insérer un nom de club");
                return;
            }

            // Check if club exists
            try {
                Club club = getClub(textInput);
                ClubPage page = new ClubPage(clubs, members, roomManager, club);
                page.setVisible(true);
                dispose();
            } catch (UnknownClubException ex) {
                errorLabel.setText("Ce club n'existe pas");
            }
        }
    }

    public Club getClub(String name) {
        for (Club club : clubs) {
            if(Objects.equals(club.getName(), name)) return club;
        }
        throw new UnknownClubException(name);
    }
}