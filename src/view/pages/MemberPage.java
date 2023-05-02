package view.pages;

import javax.swing.*;

import model.ClubManager;
import model.Member;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MemberPage extends InterfaceApp {
    private JPanel panel;
    private JLabel title;
    private JButton backButton;

    private Member currentMember;

    public MemberPage(ClubManager clubManager, Member member) {
        super("Member Page", clubManager);
        this.currentMember = member;

        // create the panel and add components
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        title = new JLabel("Bienvenue, " + currentMember.getName() + " !");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        backButton = new JButton("< Back to Home");
        panel.add(title);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(backButton);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // add action listener to the button
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // navigate back to home page
                HomePage homePage = new HomePage(clubManager);
                homePage.setVisible(true);
                dispose();
            }
        });

        // add panel to the frame and center the frame
        add(panel);
    }
}
