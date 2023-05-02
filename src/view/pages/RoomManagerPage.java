package view.pages;

import javax.swing.*;

import model.Club;
import model.ClubManager;
import model.Member;
import view.components.BoxRadius;
import view.components.Title;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RoomManagerPage extends InterfaceApp {
    private JPanel panel;
    private JLabel title;
    private JButton backButton;

    public RoomManagerPage(ClubManager clubManager) {
        super("Room Manager Page", clubManager);

        // create the panel and add components
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

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
        panel.add(backButton);

        this.addClubSection(panel);
        // create a box with a border solid of 1px and with a border radius of 5px

        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // add panel to the frame and center the frame
        add(panel);
    }

    private void addClubSection(JPanel panel){
        title = new Title("Gestionnaire de Club", 1);
        panel.add(title);

        ArrayList<Club> clubs = clubManager.getClubs();
        for(int i = 0; i < clubs.size(); i++){
            Club club = clubs.get(i);
            panel.add(new Title(club.getName(), 2));
        }
    }
}
