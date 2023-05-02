package view.pages;

import model.ClubManager;
import model.Concert;
import model.Member;
import model.Ticket;
import view.components.TicketLinePanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MemberPage extends InterfaceApp {
    private Member currentMember;
    private JPanel panel;
    private JLabel title;
    private JLabel ticketsLabel;
    private JPanel notificationsPanel;
    private JPanel ticketsPanel;
    private JLabel thresholdPriceLabel;
    private JLabel notificationsLabel;
    private JButton backButton;
    private JLabel concertName;
    private JButton cancelButton;
    private JLabel concertState;
    private JButton annulerButton;
    private JPanel ticketLine;

    public MemberPage(ClubManager clubManager, Member member) {
        super("Concert - Mon compte", clubManager);
        this.currentMember = member;

        setContentPane(panel);
        title.setText("Bienvenue, " + currentMember.getName() + " !");
        thresholdPriceLabel.setText("Prix seuil: " + currentMember.getPriceThreshold() + "€");

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // navigate back to home page
                HomePage homePage = new HomePage(clubManager);
                homePage.setVisible(true);
                dispose();
            }
        });

        updateTickets();
    }

    public void updateTickets() {
        if (currentMember.hasNoTickets()) {
            System.out.print("Pas de billets");
            ticketsPanel.add(new JLabel("Vous n'avez aucun ticket"));
        }
        ;
        ticketsPanel.add(new JLabel("Vous n'avez aucun ticket"));
        ticketsPanel.add(new TicketLinePanel(new Ticket(new Concert("test", "test", 5, 15))));

        for (Ticket ticket : currentMember.getTickets()) {
            System.out.print("billet\n");
            ticketsPanel.add(new TicketLinePanel(ticket));
        }

        // Repaint the panel to reflect the changes
        revalidate();
        repaint();
    }
}
