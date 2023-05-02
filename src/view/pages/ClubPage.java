package view.pages;

import javax.swing.*;

import model.Club;
import model.ClubManager;
import model.Concert;
import model.Room;
import view.components.BoxRadius;
import view.components.Typography;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Set;

public class ClubPage extends InterfaceApp {
    private Club club;

    public ClubPage(ClubManager clubManager, Club club) {
        super("Club Page", clubManager);
        this.club = club;

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JButton backButton = new JButton("< Back to Home");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                HomePage homePage = new HomePage(clubManager);
                dispose();
            }
        });

        panel.add(backButton);
        this.addSpacer(panel);
        panel.add(new Typography("Club " + club.getName(), 1));
        this.addSpacer(panel);

        panel.add(new Typography("Concerts", 2));
        panel.add(this.createConcertSection());

        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        this.getContentPane().add(panel);

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(scrollPane, BorderLayout.EAST);
    }

    private void refreshPage() {
        ClubPage clubPage = new ClubPage(clubManager, club);
        clubPage.setVisible(true);
        this.dispose();
    }

    private JPanel createConcertForm() {
        JPanel concertForm = new JPanel();
        concertForm.setLayout(new BoxLayout(concertForm, BoxLayout.Y_AXIS));

        concertForm.add(new Typography("Name :", 3));
        JTextField nameField = new JTextField();
        nameField.setSize(100, 30);
        concertForm.add(nameField);

        concertForm.add(new Typography("Salle :", 3));
        Set<Room> keySet = this.clubManager.getRoomManager().getRooms().keySet();
        Room[] rooms = keySet.toArray(new Room[keySet.size()]);
        JComboBox<Room> select = new JComboBox<Room>(rooms);
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
                Double newMemberPrice = Double.valueOf(priceField.getText());

                club.addConcert(new Concert(newMemberName, room, newMemberPrice));
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
                    String.valueOf(concert.getNbMaxPlaces() - concert.getNbFreePlaces()) + "/"
                            + concert.getNbMaxPlaces(),
                    3));
            concertLine.add(new Typography(String.valueOf(concert.getTicketPrice()) + "€", 3));
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
}
