package view.pages;

import javax.swing.*;

import model.Club;
import model.ClubManager;
import model.Concert;
import model.Room;
import view.components.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Set;

public class ClubPage extends InterfaceApp implements ActionListener {
    private Club club;

    private BackButtonPanel backButtonPanel = new BackButtonPanel("< Retour à l'accueil");
    public ClubPage(ClubManager clubManager, Club club) {
        super("Club Page", clubManager);
        this.club = club;

        JPanel panel = new MainPanel();

        // Back button
        backButtonPanel.getBackButton().addActionListener(this);
        panel.add(backButtonPanel);

        panel.add(new TitleLabel("Club " + club.getName()));
        panel.add(new Typography("Concerts", 2));

        panel.add(this.createConcertSection());

        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getVerticalScrollBar().setUnitIncrement(50);
        add(scrollPane);
    }

    private void refreshPage() {
        ClubPage clubPage = new ClubPage(clubManager, club);
        clubPage.setVisible(true);
        dispose();
    }

    private JPanel createConcertForm() {
        JPanel concertForm = new JPanel();
        concertForm.setLayout(new BoxLayout(concertForm, BoxLayout.Y_AXIS));

        concertForm.add(new Typography("Nom :", 3));
        JTextField nameField = new JTextField();
        nameField.setSize(100, 30);
        concertForm.add(nameField);

        concertForm.add(new Typography("Salle :", 3));
        Set<Room> keySet = this.clubManager.getRoomManager().getRooms().keySet();
        Room[] rooms = keySet.toArray(new Room[keySet.size()]);
        JComboBox<Room> select = new JComboBox<>(rooms);
        concertForm.add(select);

        concertForm.add(new Typography("Prix :", 3));
        JTextField priceField = new JTextField();
        priceField.setSize(100, 30);
        concertForm.add(priceField);

        JButton validationButton = new JButton("Ajouter");
        validationButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String newMemberName = nameField.getText();
                Room room = (Room) select.getSelectedItem();
                double newMemberPrice = Double.parseDouble(priceField.getText());

                Concert concert = new Concert(newMemberName, room, newMemberPrice);
                club.addConcert(concert);
                club.informMembers(concert);
                refreshPage();
            }
        });
        concertForm.add(validationButton);

        return concertForm;
    }

    private BoxRadius createConcertSection() {
        BoxRadius section = new BoxRadius();

        ArrayList<Concert> concerts = this.club.getConcert();
        JPanel concertsContainer = new JPanel();
        concertsContainer.setLayout(new BoxLayout(concertsContainer, BoxLayout.Y_AXIS));

        for (Concert concert : concerts) {
            JPanel concertLine = new JPanel();
            concertLine.setLayout(new FlowLayout());

            concertLine.add(new Typography(concert.getName(), 3));
            concertLine.add(new Typography(
                    concert.getNbMaxPlaces() - concert.getNbFreePlaces() + "/"
                            + concert.getNbMaxPlaces(),
                    3));
            concertLine.add(new Typography(concert.getTicketPrice() + "€", 3));
            JButton cancelButton = new JButton("Annuler");
            cancelButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // do something here
                }
            });
            concertLine.add(cancelButton);

            concertsContainer.add(concertLine);
            this.addSpacer(concertsContainer);
        }

        concertsContainer.add(this.createConcertForm());

        section.add(concertsContainer);
        return section;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Object src = event.getSource();
        if(src == backButtonPanel.getBackButton()) {
            new HomePage(clubManager);
            dispose();
        }
    }
}
