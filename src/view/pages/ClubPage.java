package view.pages;

import model.*;
import model.exceptions.NegativePriceException;
import model.exceptions.PassedDateException;
import model.exceptions.RoomAlreadyBookedException;
import model.exceptions.RoomNotBookedException;
import view.components.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ClubPage extends InterfaceApp implements ActionListener {
    private Club currentClub;
    private JButton validationButton = new PrimaryButton("Ajouter");
    private JTextField nameInput = new DefaultTextField();
    private JTextField dateInput = new DefaultTextField();
    private JComboBox<Room> roomComboBox;
    private JTextField priceInput = new DefaultTextField();
    private JPanel concertsPanel = new DefaultPanel();
    private BackButtonPanel backButtonPanel;

    public ClubPage(ArrayList<Club> clubs, ArrayList<Member> members, RoomManager roomManager, Club club) {
        super("Mon club - " + club.getName(), clubs, members, roomManager);
        club.setWindow(this);
        this.currentClub = club;

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
        ArrayList<Room> rooms = roomManager.getRooms();
        roomComboBox = new JComboBox<>(rooms.toArray(new Room[0]));
        JPanel roomField = new DefaultInputField(new DefaultLabel("Salle"), roomComboBox);
        concertForm.add(roomField);
        concertForm.add(Box.createVerticalStrut(10));

        // Date field
        dateInput.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
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
        for (Concert concert : currentClub.getConcerts()) {
            ConcertLine concertLine = new ConcertLine(concert);

            concertsPanel.add(concertLine);
            concertLine.getCancelButton().addActionListener(e -> {
                try {
                    concert.getRoom().unbook(concert.getDate());
                } catch (RoomNotBookedException ex) {
                    showErrorMessage(ex.getMessage());
                    return;
                }
                currentClub.removeConcert(concert);
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
            new HomePage(clubs, members, roomManager);
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
                    // Check if price is negative
                    if(concertPrice < 0) throw new NegativePriceException();
                } catch (NumberFormatException e) {
                    error_message += "Le prix doit être un nombre\n";
                } catch (NegativePriceException e) {
                    error_message += e.getMessage() + "\n";
                }
            }

            // Validate concert name
            if(concertName.isEmpty()) error_message += "Veuillez préciser un nom de concert\n";

            // Validate room choice
            if(room == null) error_message += "Veuillez sélectionner une salle";

            // Validate concert date
            Date concertDate = null;
            try {
                // Parse dateInput string into Date
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                concertDate = formatter.parse(dateInput.getText());
                // Check if date is passed
                if(concertDate.before(new Date())) throw new PassedDateException();
            } catch (ParseException e) {
                error_message += "La date doit être valide, en format jj/mm/yyyy";
            } catch (PassedDateException e) {
                error_message += e.getMessage();
            }

            // Displays errors and ends the method if there's any
            if(!error_message.isEmpty()) {
                showErrorMessage(error_message);
                return;
            }

            // Attempts book the room on the given date
            try {
                room.book(concertDate);
            } catch (RoomAlreadyBookedException ex) {
                showErrorMessage(ex.getMessage());
                return;
            }

            // Adds the new concert in the club concert's list
            Concert newConcert = new Concert(concertName, room, concertPrice, concertDate);
            currentClub.addConcert(newConcert);

            updateConcerts();
        }
    }
}
