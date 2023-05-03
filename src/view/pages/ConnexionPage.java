package view.pages;

import model.Club;
import model.ClubManager;
import model.Member;
import model.exceptions.UnknownClubException;
import model.exceptions.UnknownMemberException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConnexionPage extends InterfaceApp implements ActionListener {
    private JPanel mainPanel;
    private JPanel connexionPanel;
    private JLabel title;
    private JLabel nameLabel;
    private JTextField nameInput;
    private JButton connectButton;
    private JLabel errorLabel;
    private JButton backButton;

    private String userType;

    public ConnexionPage(ClubManager clubManager, String userType) {
        super("Concert Manager - Connexion", clubManager);
        setContentPane(mainPanel);
        this.userType = userType;

        title.setText("Connexion - " + (userType.equals("member") ? "Membre" : "Club"));
        connectButton.addActionListener(this);
        backButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Object src = event.getSource();
        if(src == connectButton) {
            String textInput = nameInput.getText().toLowerCase();

            // Validates input
            if (textInput.isEmpty()) {
                errorLabel.setText("Veuillez insérer un nom de " + (userType.equals("member") ? "membre" : "club"));
                return;
            }

            // Check if user exists
            if (userType.equals("member")) {
                try {
                    Member member = clubManager.getMember(textInput);
                    MemberPage page = new MemberPage(clubManager, member);
                    page.setVisible(true);
                    dispose();
                } catch (UnknownMemberException ex) {
                    errorLabel.setText("Ce membre n'existe pas");
                }

                // Check if club exists
            } else if (userType.equals("club")) {
                try {
                    Club club = clubManager.getClub(textInput);
                    ClubPage page = new ClubPage(clubManager, club);
                    page.setVisible(true);
                    dispose();
                } catch (UnknownClubException ex) {
                    errorLabel.setText("Ce club n'existe pas");
                }
            }
        }
        if(src == backButton) {
            new HomePage(clubManager);
            dispose();
        }

    }
}
