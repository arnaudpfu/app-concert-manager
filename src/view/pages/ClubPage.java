package view.pages;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import model.Club;
import model.ClubManager;
import model.Concert;
import model.Room;
import model.exceptions.FullRoomException;
import view.components.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

public class ClubPage extends InterfaceApp implements ActionListener {
    private Club club;
    private JButton validationButton = new PrimaryButton("Ajouter");
    private JTextField nameInput = new DefaultTextField();
    private JComboBox<Room> roomComboBox;
    private JTextField priceInput = new DefaultTextField();
    private JPanel concertsPanel = new JPanel();

    private BackButtonPanel backButtonPanel = new BackButtonPanel("< Retour à l'accueil");
    public ClubPage(ClubManager clubManager, Club club) {
        super("Club Page", clubManager);
        this.club = club;
        this.clubManager = clubManager;

        JPanel panel = new MainPanel();

        // Back button
        backButtonPanel.getBackButton().addActionListener(this);
        panel.add(backButtonPanel);

        // Top panel (Name of the club and "Concerts" title)
        panel.add(new TitleLabel("Club " + club.getName()));
        panel.add(new TitleLabel("Concerts"));

        // Concerts list and concert creation form
        concertsPanel.setBackground(new Color(46,46,46));
        updateConcerts();

        panel.add(concertsPanel);
        panel.add(createConcertForm());

        // Making the window scrollable
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getVerticalScrollBar().setUnitIncrement(50);
        setContentPane(scrollPane);
    }

    private JPanel createConcertForm() {
        JPanel concertForm = new JPanel();
        concertForm.setLayout(new BoxLayout(concertForm, BoxLayout.Y_AXIS));
        concertForm.setBackground(new Color(46,46,46));
        concertForm.setBorder(new EmptyBorder(30,30,30,30));

        JPanel nameField = new DefaultInputField(new DefaultLabel("Nom"), nameInput);
        concertForm.add(nameField);
        concertForm.add(Box.createVerticalStrut(10));

        Set<Room> keySet = this.clubManager.getRoomManager().getRooms().keySet();
        Room[] rooms = keySet.toArray(new Room[keySet.size()]);
        roomComboBox = new JComboBox<>(rooms);
        JPanel roomField = new DefaultInputField(new DefaultLabel("Salle"), roomComboBox);
        concertForm.add(roomField);
        concertForm.add(Box.createVerticalStrut(10));

        JPanel priceField = new DefaultInputField(new DefaultLabel("Prix"), priceInput);
        concertForm.add(priceField);
        concertForm.add(Box.createVerticalStrut(10));

        validationButton.addActionListener(this);
        concertForm.add(validationButton);

        return concertForm;
    }

    private void updateConcerts() {
        concertsPanel.removeAll();

        // Creating concerts panel
        concertsPanel.setLayout(new BoxLayout(concertsPanel, BoxLayout.Y_AXIS));

        // Populating the concerts panel
        for (Concert concert : club.getConcerts()) {
            ConcertLine concertLine = new ConcertLine(concert);

            concertsPanel.add(concertLine);
            concertLine.getCancelButton().addActionListener(e -> {
                clubManager.attemptRemoveConcert(club, concert);
                updateConcerts();
            });
        }

        // Applying changes to the window
        concertsPanel.repaint();
        concertsPanel.revalidate();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Object src = event.getSource();
        // Back button (goes back to HomePage)
        if(src == backButtonPanel.getBackButton()) {
            new HomePage(clubManager);
            dispose();
        }

        // Concert creation
        if(src == validationButton) {
            String concertName = nameInput.getText();
            Room room = (Room) roomComboBox.getSelectedItem();

            // Validate inputs
            String error_message = "";
            double concertPrice = 0;
            if(priceInput.getText().isEmpty())  error_message += "Veuillez préciser un prix de concert\n";
            else {
                try {
                    concertPrice = Double.parseDouble(priceInput.getText());
                } catch (Exception e) {
                    error_message += "Le prix doit être un nombre\n";
                }
            }
            if(concertName.isEmpty()) error_message += "Veuillez préciser un nom de concert";

            // Display errors
            if(!error_message.isEmpty()) {
                showErrorMessage(error_message);
                return;
            }

            // Attempt to create concert
            try {
                clubManager.attemptNewConcert(club, room, concertName, concertPrice);
            } catch (FullRoomException ex) {
                showErrorMessage("La salle " + room.getName() + " est déjà réservé par un club");
                return;
            }
            updateConcerts();
        }
    }
}
