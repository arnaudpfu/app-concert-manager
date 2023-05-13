package view.pages;

import model.Club;
import model.ClubManager;
import model.Concert;
import model.Room;
import model.exceptions.FullRoomException;
import view.components.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

public class ClubPage extends InterfaceApp implements ActionListener {
    private Club club;
    private JButton validationButton = new PrimaryButton("Ajouter");
    private JTextField nameInput = new DefaultTextField();
    private JTextField dateInput = new DefaultTextField();
    private JComboBox<Room> roomComboBox;
    private JTextField priceInput = new DefaultTextField();
    private JPanel concertsPanel = new DefaultPanel();

    private BackButtonPanel backButtonPanel;
    public ClubPage(ClubManager clubManager, Club club) {
        super("Mon club - " + club.getName(), clubManager);
        club.setWindow(this);
        this.club = club;
        this.clubManager = clubManager;

        // Back button
        backButtonPanel = new BackButtonPanel("< Retour à l'accueil", this);
        mainPanel.add(backButtonPanel);

        // Top panel (Name of the club and "Concerts" title)
        mainPanel.add(new TitleLabel("Club " + club.getName()));
        mainPanel.add(new TitleLabel("Concerts"));

        // Concerts list and concert creation form
        updateConcerts();

        mainPanel.add(concertsPanel);
        mainPanel.add(createConcertForm());

        // Making the window scrollable
        endFrameCreation();
    }

    private JPanel createConcertForm() {
        // Form container
        JPanel concertForm = new DefaultPanel(30);

        // Name field
        JPanel nameField = new DefaultInputField(new DefaultLabel("Nom"), nameInput);
        concertForm.add(nameField);
        concertForm.add(Box.createVerticalStrut(10));

        // Room field (ComboBox)
        Set<Room> keySet = this.clubManager.getRoomManager().getRooms().keySet();
        Room[] rooms = keySet.toArray(new Room[keySet.size()]);
        roomComboBox = new JComboBox<>(rooms);
        JPanel roomField = new DefaultInputField(new DefaultLabel("Salle"), roomComboBox);
        concertForm.add(roomField);
        concertForm.add(Box.createVerticalStrut(10));

        // Date field
        JPanel dateField = new DefaultInputField(new DefaultLabel("Date"), dateInput);
        concertForm.add(dateField);
        concertForm.add(Box.createVerticalStrut(10));

        // Price field
        JPanel priceField = new DefaultInputField(new DefaultLabel("Prix"), priceInput);
        concertForm.add(priceField);
        concertForm.add(Box.createVerticalStrut(10));

        // Submit button
        validationButton.addActionListener(this);
        concertForm.add(validationButton);

        return concertForm;
    }

    public void updateConcerts() {
        concertsPanel.removeAll();

        // Populating the concerts panel
        for (Concert concert : club.getConcerts()) {
            System.out.println(concert);
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

            // Validate concert price
            double concertPrice = 0;
            if(priceInput.getText().isEmpty())  error_message += "Veuillez préciser un prix de concert\n";
            else {
                try {
                    concertPrice = Double.parseDouble(priceInput.getText());
                } catch (Exception e) {
                    error_message += "Le prix doit être un nombre\n";
                }
            }

            // Validate concert name
            if(concertName.isEmpty()) error_message += "Veuillez préciser un nom de concert\n";

            // Validate concert date
            Date concertDate = null;
            try {
                // Parse dateInput string into Date
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                concertDate = formatter.parse(dateInput.getText());
            } catch (ParseException e) {
                error_message += "La date doit être valide, en format jj/mm/yyyy";
            }

            // Display errors
            if(!error_message.isEmpty()) {
                showErrorMessage(error_message);
                return;
            }

            // Attempt to create concert
            try {
                clubManager.attemptNewConcert(club, room, concertName, concertPrice, concertDate);
            } catch (FullRoomException ex) {
                showErrorMessage("La salle " + room.getName() + " est déjà réservé par un club ce jour là");
                return;
            }
            updateConcerts();
        }
    }
}
