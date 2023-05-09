package view.pages;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import model.*;
import model.exceptions.AlreadyBookedException;
import model.exceptions.FullConcertException;
import view.components.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class MemberPage extends InterfaceApp implements ActionListener {

    // --------- PANELS --------- //
    private JPanel mainPanel = new MainPanel();
    private JPanel topPanel = new JPanel(new GridLayout(1, 0));
    private JPanel contentPanelContainer = new JPanel(new GridLayout());
    private JPanel contentPanel = new JPanel(new GridLayout(0, 1));
    private JPanel ticketsPanel = new JPanel();
    private JPanel notificationsPanel = new JPanel();
    private JPanel newConcertsPanel = new JPanel();

    // --------- LABELS --------- //

    private JLabel title = new TitleLabel("Bienvenue !");
    private JLabel thresholdPriceLabel = new DefaultLabel("Prix seuil :");
    private JLabel notificationsTitle = new TitleLabel("Notifications");
    private JLabel ticketsTitle = new TitleLabel("Vos billets");
    private JLabel newConcertsTitle = new TitleLabel("Nouveaux concerts");

    // --------- OTHERS --------- //

    private BackButtonPanel backButtonPanel;
    private JButton refreshButton = new JButton("Refresh");
    private Member currentMember;
    private ArrayList<String> notifications = new ArrayList<>();

    public MemberPage(ClubManager clubManager, Member member) {
        super("Concert - Mon compte", clubManager);
        member.setWindow(this);
        this.currentMember = member;
        setSize(new Dimension(1000, 800));

        // Back button
        backButtonPanel = new BackButtonPanel("< Déconnexion");
        backButtonPanel.getBackButton().addActionListener(this);
        mainPanel.add(backButtonPanel);

        // TODO : Remove refresh button
        refreshButton.addActionListener(this);
        mainPanel.add(refreshButton);

        // Top panel (title and price threshold)
        topPanel.setForeground(new Color(187, 187, 187));
        topPanel.setOpaque(false);
        title.setText("Bienvenue, " + currentMember.getName() + " !");
        thresholdPriceLabel.setText("Prix seuil: " + currentMember.getPriceThreshold() + "€");
        topPanel.add(title);
        topPanel.add(Box.createHorizontalStrut(0));
        topPanel.add(thresholdPriceLabel);
        mainPanel.add(topPanel);

        // Content panel (notifications, tickets and new concerts)
        contentPanel.setBorder(new EmptyBorder(50, 50, 50, 50));
        contentPanel.setBackground(new Color(46,46,46));
        contentPanelContainer.setOpaque(false);
        contentPanelContainer.setBackground(new Color(46,46,46));

        ticketsPanel.setLayout(new BoxLayout(ticketsPanel, BoxLayout.Y_AXIS));
        newConcertsPanel.setLayout(new BoxLayout(newConcertsPanel, BoxLayout.Y_AXIS));
        notificationsPanel.setLayout(new BoxLayout(notificationsPanel, BoxLayout.Y_AXIS));

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

        // Making the main panel scrollable
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(50);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.add(scrollPane);

        this.setLocationRelativeTo(null);
    }

    /** Updates the tickets panel **/
    public void updateTickets() {
        ticketsPanel.removeAll();
        if(currentMember.hasNoTickets()) {
            ticketsPanel.add(new JLabel("Aucun ticket"));
        }

        for (Ticket ticket : currentMember.getTickets()) {
            JPanel ticketPanel = new JPanel();
            ticketPanel.add(new JLabel(ticket.getConcert().getName()));
            JButton cancelButton = new SecondaryButton("Annuler");
            cancelButton.addActionListener(e -> {
                clubManager.attempRemoveReservation(currentMember, ticket);
                updateTickets();
                updateConcerts();
            });
            ticketPanel.add(cancelButton);
            ticketPanel.add(new JLabel("En cours"));
            ticketsPanel.add(ticketPanel);
            ticketsPanel.revalidate();
        }
        ticketsPanel.repaint();
    }

    /** Updates the notifications panel **/
    public void updateNotifications() {
        notificationsPanel.removeAll();
        if(notifications.isEmpty()) {
            notificationsPanel.add(new JLabel("Aucune notification"));
        }
        for (String message: notifications) {
            notificationsPanel.add(new DefaultLabel(message, true));
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
        newConcertsPanel.removeAll();
        for (Concert concert: clubManager.getConcerts()) {
            if(!currentMember.hasReserved(concert)) {
                JPanel concertPanel = new JPanel();
                concertPanel.add(new JLabel(concert.getName()));
                concertPanel.add(new JLabel(concert.getTicketPrice() + "€"));
                JButton reserveButton = new SecondaryButton("Réserver");
                reserveButton.addActionListener(e -> {
                    try {
                        clubManager.attemptReservation(currentMember, concert);
                    } catch (FullConcertException ex) {
                        showErrorMessage(ex.getMessage());
                    } catch (AlreadyBookedException ex) {
                        showErrorMessage("Vous avez déjà un ticket pour ce concert !");
                    }
                });
                concertPanel.add(reserveButton);
                newConcertsPanel.add(concertPanel);
            }
        }
        newConcertsPanel.repaint();
        newConcertsPanel.revalidate();
        revalidate();
    }
    @Override
    public void actionPerformed(ActionEvent event) {
        if(event.getSource() == backButtonPanel.getBackButton()) {
            new HomePage(clubManager);
            dispose();
        }
        if(event.getSource() == refreshButton) {
            updateTickets();
            updateConcerts();
            updateNotifications();
        }
    }

    @Override
    public void dispose() {
        currentMember.setWindow(null);
        super.dispose();
    }
}
