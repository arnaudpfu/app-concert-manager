package view.pages;

import model.ClubManager;
import model.Member;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MemberPage extends InterfaceApp implements ActionListener {
    private JButton disconnectButton;
    private JPanel connexionPanel;
    private JLabel title;
    private JPanel mainPanel;
    private JLabel priceThresholdLabel;
    private JPanel topPanel;
    private JPanel notificationsPanel;
    private JPanel ticketsPanel;
    private JPanel newConcertsPanel;

    private Member currentMember;
    public MemberPage(ClubManager clubManager, Member member) {
        super("Concert Manager - Mon compte", clubManager);
        this.currentMember = member;
        setContentPane(mainPanel);

        title.setText("Bienvenue, " + currentMember.getName() + " !");
        priceThresholdLabel.setText("Prix seuil: " + currentMember.getPriceThreshold() + "€");

        disconnectButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Object src = event.getSource();
        if(src == disconnectButton) {
            new ConnexionPage(clubManager, "member");
            dispose();
        }
    }
}
