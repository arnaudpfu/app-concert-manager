package view.pages;

import javax.swing.*;

import model.*;
import model.exceptions.MemberAlreadyBookedException;
import model.exceptions.FullConcertException;
import model.exceptions.NoMoneyException;
import view.components.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class MemberPage extends InterfaceApp implements ActionListener {

    // --------- PANELS --------- //
    private JPanel topPanel, contentPanelContainer, contentPanel, ticketsPanel, notificationsPanel, newConcertsPanel;

    // --------- LABELS --------- //

    private JLabel title = new TitleLabel("Bienvenue !");
    private JLabel thresholdPriceLabel = new DefaultLabel("Prix seuil :");
    private JLabel notificationsTitle = new TitleLabel("Notifications");
    private JLabel ticketsTitle = new TitleLabel("Vos billets");
    private JLabel newConcertsTitle = new TitleLabel("Nouveaux concerts");

    // --------- OTHERS --------- //
    private BackButtonPanel backButtonPanel;
    private Member currentMember;
    private ArrayList<String> notifications = new ArrayList<>();

    public MemberPage(ArrayList<Club> clubs, ArrayList<Member> members, RoomManager roomManager, Member member) {
        super("Mon compte - " + member.getName(), clubs, members, roomManager);
        member.setWindow(this);
        this.currentMember = member;

        // Back button
        backButtonPanel = new BackButtonPanel("< Déconnexion", this);
        mainPanel.add(backButtonPanel);

        // Top panel
        topPanel = new DefaultPanel(false);
        title.setText("Bienvenue, " + currentMember.getNameFormat() + " !");
        thresholdPriceLabel.setText("Prix seuil: " + currentMember.getPriceThreshold() + "€");
        topPanel.add(title);
        topPanel.add(thresholdPriceLabel);
        mainPanel.add(topPanel);

        // Content panel (notifications, tickets and new concerts)
        contentPanel = new DefaultPanel(50);
        contentPanelContainer = new DefaultPanel(false);
        ticketsPanel = new DefaultPanel(41,41,41,10);
        newConcertsPanel = new DefaultPanel(41,41,41,10);
        notificationsPanel = new DefaultPanel(41,41,41,10);

        updateNotifications();
        updateTickets();
        updateConcerts();

        contentPanel.add(notificationsTitle);
        contentPanel.add(notificationsPanel);
        contentPanel.add(ticketsTitle);
        contentPanel.add(ticketsPanel);
        contentPanel.add(newConcertsTitle);
        contentPanel.add(newConcertsPanel);

        // Centering the content panel inside the main panel
        GridBagConstraints cpcConstraints = new GridBagConstraints();
        cpcConstraints.fill = GridBagConstraints.VERTICAL;
        cpcConstraints.anchor = GridBagConstraints.CENTER;
        contentPanelContainer.add(contentPanel, cpcConstraints);
        mainPanel.add(contentPanelContainer);

        // Making the window scrollable
        endFrameCreation();
    }

    /** Updates the tickets panel **/
    public void updateTickets() {
        ticketsPanel.removeAll();
        if(currentMember.hasNoTickets()) {
            ticketsPanel.add(new DefaultLabel("Aucun ticket"));
        }

        for (Ticket ticket : currentMember.getTickets()) {
            JPanel ticketPanel = new JPanel();
            ticketPanel.setOpaque(false);
            ticketPanel.add(new DefaultLabel(ticket.getConcert().getName()));
            ticketPanel.add(new DefaultLabel("En cours"));
            JButton cancelButton = new SecondaryButton("Annuler");
            cancelButton.addActionListener(e -> {
                currentMember.unbook(ticket);
                updateTickets();
                updateConcerts();
            });
            ticketPanel.add(cancelButton);
            ticketsPanel.add(ticketPanel);
            ticketsPanel.revalidate();
        }
        ticketsPanel.repaint();
    }

    /** Updates the notifications panel **/
    public void updateNotifications() {
        notificationsPanel.removeAll();
        if(notifications.isEmpty()) {
            notificationsPanel.add(new DefaultLabel("Aucune notification"));
        }
        for (String message: notifications) {
            notificationsPanel.add(new DefaultLabel(message));
        }
        notificationsPanel.repaint();
        notificationsPanel.revalidate();
    }

    /** Removes a concert from the notifications panel (likely due to a concert annulation) **/
    public void removeNotification(String notification) {
        notifications.remove(notification);
        updateNotifications();
    }

    /** Adds a message notifications panel **/
    public void addNotification(String notification) {
        notifications.add(notification);
        updateNotifications();
    }

    /** Updates the concerts panel **/
    public void updateConcerts() {
        // Remove all concerts lines
        newConcertsPanel.removeAll();

        for(Club club : clubs) {
            for (Concert concert: club.getConcerts()) {
                // If member hasn't booked yet
                if(currentMember.hasBooked(concert)) continue;

                // Add a concert line
                JPanel concertPanel = new JPanel();
                concertPanel.setOpaque(false);
                concertPanel.add(new DefaultLabel(concert.getName()));
                concertPanel.add(Box.createRigidArea(new Dimension(50, 20)));
                concertPanel.add(new DefaultLabel(concert.getDateFormat()));
                concertPanel.add(Box.createRigidArea(new Dimension(50, 20)));
                concertPanel.add(new DefaultLabel("Salle " + concert.getRoom().getName()));
                concertPanel.add(Box.createRigidArea(new Dimension(50, 20)));
                concertPanel.add(new DefaultLabel(concert.getTicketPrice() + "€"));
                concertPanel.add(Box.createRigidArea(new Dimension(50, 20)));
                JButton reserveButton = new PrimaryButton("Réserver");
                reserveButton.addActionListener(e -> {
                    try {
                        currentMember.book(concert);
                    } catch (FullConcertException ex) {
                        showErrorMessage(ex.getMessage());
                    } catch (MemberAlreadyBookedException ex) {
                        showErrorMessage("Vous avez déjà un ticket pour ce concert !");
                    } catch (NoMoneyException ex) {
                        showErrorMessage("Vous n'avez pas un seuil suffisant pour réserver ce concert");
                    }
                });
                concertPanel.add(reserveButton);
                newConcertsPanel.add(concertPanel);
            }
        }
        newConcertsPanel.repaint();
        newConcertsPanel.revalidate();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if(event.getSource() == backButtonPanel.getBackButton()) {
            new HomePage(clubs, members, roomManager);
            dispose();
        }
    }

    @Override
    public void dispose() {
        currentMember.setWindow(null);
        super.dispose();
    }
}
