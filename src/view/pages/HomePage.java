package view.pages;

import model.ClubManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage extends InterfaceApp implements ActionListener {
    private JButton memberButton;
    private JButton clubButton;
    private JButton roomManagerButton;
    private JPanel mainPanel;

    public HomePage(ClubManager clubManager) {
        super("Concert Manager - Connexion", clubManager);
        setContentPane(mainPanel);

        memberButton.addActionListener(this);
        clubButton.addActionListener(this);
        roomManagerButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent event) {
        // Redirects on the right page
        Object src = event.getSource();
        InterfaceApp redirectionPage = null;

        if (src == memberButton) {
            redirectionPage = new ConnexionPage(clubManager, "member");
        } else if (src == clubButton ) {
            redirectionPage = new ConnexionPage(clubManager, "club");
        } else if (src == roomManagerButton) {
            redirectionPage = new RoomManagerPage(clubManager);
        }

        redirectionPage.setVisible(true);
        dispose();
    }
}
