package view.pages;

import javax.swing.*;

import model.ClubManager;
import model.Concert;
import model.Member;
import model.Ticket;
import view.components.Typography;

import java.awt.event.*;

public class MemberPage extends InterfaceApp implements ActionListener {
    private JLabel title = new Typography("Membre", 3);
    private JLabel thresholdPriceLabel = new JLabel("Prix seuil :");
    private JLabel notificationsLabel = new Typography("Notifications", 3);
    private JLabel ticketsLabel = new Typography("Vos billets", 3);
    private JLabel newConcertsLabel = new Typography("Nouveaux concerts", 3);
    private JPanel mainPanel = new JPanel();
    private JPanel ticketsPanel = new JPanel();
    private JPanel notificationsPanel = new JPanel();
    private JPanel newConcertsPanel = new JPanel();
    private Member currentMember;
    private JButton backButton = new JButton("< Retour à l'accueil");

    public MemberPage(ClubManager clubManager, Member member) {
        super("Concert - Mon compte", clubManager);
        this.currentMember = member;

        title.setText("Bienvenue, " + currentMember.getName() + " !");
        thresholdPriceLabel.setText("Prix seuil: " + currentMember.getPriceThreshold() + "€");

        backButton.addActionListener(this);

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        mainPanel.add(backButton);
        mainPanel.add(title);
        mainPanel.add(thresholdPriceLabel);

        // Notifications panel
        mainPanel.add(notificationsLabel);
        this.updateNotifications();
        mainPanel.add(notificationsPanel);

        // Tickets panel
        mainPanel.add(ticketsLabel);
        ticketsPanel.setLayout(new BoxLayout(ticketsPanel, BoxLayout.Y_AXIS));
        this.updateTickets();
        mainPanel.add(ticketsPanel);

        // New concerts panel
        mainPanel.add(newConcertsLabel);
        newConcertsPanel.setLayout(new BoxLayout(newConcertsPanel, BoxLayout.Y_AXIS));
        this.updateConcerts();
        mainPanel.add(newConcertsPanel);

        this.add(mainPanel);
        this.setLocationRelativeTo(null);
    }

    /** Updates the tickets panel **/
    public void updateTickets() {
        for (Ticket ticket : currentMember.getTickets()) {
            JPanel ticketPanel = new JPanel();
            ticketPanel.add(new JLabel(ticket.getConcert().getName()));
            ticketPanel.add(new JButton("Annuler"));
            ticketPanel.add(new JLabel("En cours"));
            ticketsPanel.add(ticketPanel);
        }
    }

    /** Updates the notifications panel **/
    public void updateNotifications() {
        notificationsPanel.add(new JLabel("Aucune notification"));
    }

    /** Updates the concerts panel **/
    public void updateConcerts() {
        for (Concert concert: clubManager.getConcerts()) {
            if(!currentMember.hasReserved(concert)) {
                JPanel concertPanel = new JPanel();
                concertPanel.add(new JLabel(concert.getName()));
                concertPanel.add(new JLabel(concert.getTicketPrice() + "€"));
                concertPanel.add(new JButton("Réserver"));
                newConcertsPanel.add(concertPanel);
            }
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        HomePage homePage = new HomePage(clubManager);
        homePage.setVisible(true);
        dispose();
    }
}
