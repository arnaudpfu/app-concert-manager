package view.pages;

import javax.swing.*;

import model.Club;
import model.ClubManager;
import model.Member;
import model.exceptions.UnknownClubException;
import model.exceptions.UnknownMemberException;

import java.awt.event.*;

public class ConnexionPage extends InterfaceApp implements ActionListener {
    private JLabel title;
    private JLabel nameLabel;
    private JTextField nameField;
    private JButton submitButton;
    private JButton backButton;
    private String userType;

    public ConnexionPage(ClubManager clubManager, String userType) {
        super("Connexion", clubManager);
        this.userType = userType;
        this.setTitle("Connexion - " + (userType.equals("member") ? "Membre" : "Club"));

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        backButton = new JButton("< Back to Home");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // navigate back to home page
                HomePage homePage = new HomePage(clubManager);
                homePage.setVisible(true);
                dispose();
            }
        });

        title = new JLabel("Connexion");
        title.setHorizontalAlignment(JLabel.CENTER);

        nameLabel = new JLabel("Votre nom");
        nameLabel.setHorizontalAlignment(JLabel.CENTER);

        nameField = new JTextField();
        nameField.setHorizontalAlignment(JTextField.CENTER);

        submitButton = new JButton("Se connecter");
        submitButton.addActionListener(this);

        panel.add(backButton);
        panel.add(title);
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(submitButton);

        this.add(panel);
        this.setLocationRelativeTo(null);
    }

    /**
     * Method called when the user tries to connect (as either a club or a member)
     **/
    public void actionPerformed(ActionEvent event) {
        String textInput = nameField.getText().toLowerCase();

        // Validates input
        if (textInput.isEmpty()) {
            // TODO : Better error message
            showErrorMessage("Veuillez insérer un nom de " + (userType.equals("member") ? "membre" : "club"));
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
                // TODO : Better error message
                showErrorMessage("Ce membre n'existe pas");
            }

            // Check if club exists
        } else if (userType.equals("club")) {
            try {
                Club club = clubManager.getClub(textInput);
                ClubPage page = new ClubPage(clubManager, club);
                page.setVisible(true);
                dispose();
            } catch (UnknownClubException ex) {
                // TODO : Better error message
                showErrorMessage("Ce club n'existe pas");
            }
        }
    }
}
