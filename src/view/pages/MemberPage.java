package view.pages;

import model.*;
import view.components.DefaultLabel;
import view.components.SecondaryButton;

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
    private JTable ticketsTable;

    private Member currentMember;
    public MemberPage(ClubManager clubManager, Member member) {
        super("Concert Manager - Mon compte", clubManager);
        this.currentMember = member;
        setContentPane(mainPanel);

        title.setText("Bienvenue, " + currentMember.getName() + " !");
        priceThresholdLabel.setText("Prix seuil: " + currentMember.getPriceThreshold() + "€");

        disconnectButton.addActionListener(this);
        // TODO : other updates
//        updateConcertsPanel();
//        updateNotificationsPanel();
        // TODO : Delete manual add
        currentMember.addTicket(new Ticket(new Concert("Test", new Room("TestRoom",15), 15)));
        updateTicketsPanel();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Object src = event.getSource();
        if(src == disconnectButton) {
            new ConnexionPage(clubManager, "member");
            dispose();
        }
    }

    public void updateTicketsPanel() {
        if(currentMember.hasNoTickets()) {
            ticketsPanel.add(new DefaultLabel("Aucun ticket"));
        }

        for (Ticket ticket : currentMember.getTickets()) {
            JPanel ticketPanel = new JPanel();
            ticketPanel.setOpaque(false);
            ticketPanel.add(new DefaultLabel(ticket.getConcert().getName()));
            ticketPanel.add(new SecondaryButton("Annuler"));
            ticketPanel.add(new DefaultLabel("En cours"));
            ticketsPanel.add(ticketPanel);
        }
    }
}
