package view.pages;

import model.ClubManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage extends InterfaceApp {
    private JButton memberButton;
    private JButton clubButton;
    private JButton roomManagerButton;
    private JPanel mainPanel;

    public HomePage(ClubManager clubManager) {
        super("Concert Manager - Connexion", clubManager);
        setContentPane(mainPanel);

        memberButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // navigate to member page
                ConnexionPage connexionPage = new ConnexionPage(clubManager, "member");
                connexionPage.setVisible(true);
                dispose();
            }
        });
        clubButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // navigate to club page
                ConnexionPage connexionPage = new ConnexionPage(clubManager, "club");
                connexionPage.setVisible(true);
                dispose();
            }
        });
        roomManagerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // navigate to room manager page
                RoomManagerPage roomManagerPage = new RoomManagerPage(clubManager);
                roomManagerPage.setVisible(true);
                dispose();
            }
        });
    }
}
