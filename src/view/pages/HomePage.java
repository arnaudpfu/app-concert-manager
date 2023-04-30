package view.pages;

import javax.swing.*;

import model.ClubManager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage extends InterfaceApp {
    private JPanel panel;
    private JLabel title;
    private JButton memberButton;
    private JButton clubButton;
    private JButton roomManagerButton;

    public HomePage(ClubManager clubManager) {
        super("Concert Manager", clubManager);

        // create the panel and add components
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        title = new JLabel("Concert Manager");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        memberButton = new JButton("I am a member");
        clubButton = new JButton("I am a club");
        roomManagerButton = new JButton("I am the room manager");
        panel.add(title);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(memberButton);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(clubButton);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(roomManagerButton);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // add action listeners to the buttons
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

        // add panel to the frame and center the frame
        this.add(panel);
    }
}
