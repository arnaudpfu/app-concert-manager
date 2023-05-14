package view.pages;

import model.Club;
import model.Member;
import model.RoomManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class HomePage extends InterfaceApp implements ActionListener {
    private JButton memberButton;
    private JButton clubButton;
    private JButton roomManagerButton;
    private JPanel mainPanel;

    public HomePage(ArrayList<Club> clubs, ArrayList<Member> members, RoomManager roomManager) {
        super("Concert Manager", clubs, members, roomManager);

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
            redirectionPage = new ConnexionPageMember(clubs, members, roomManager);
        } else if (src == clubButton ) {
            redirectionPage = new ConnexionPageClub(clubs, members, roomManager);
        } else if (src == roomManagerButton) {
            redirectionPage = new RoomManagerPage(clubs, members, roomManager);
        }

        redirectionPage.setVisible(true);
    }
}
