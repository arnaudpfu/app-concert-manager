package view.pages;

import javax.swing.*;

import model.Club;
import model.ClubManager;
import model.Concert;
import model.Room;
import view.components.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

public class ClubPage extends InterfaceApp implements ActionListener {
    private Club club;
    private JButton validationButton = new JButton("Ajouter");
    private JTextField nameField = new JTextField();
    private JComboBox<Room> roomComboBox;
    private JTextField priceField = new JTextField();
    private JPanel concertsPanel = new JPanel();

    private BackButtonPanel backButtonPanel = new BackButtonPanel("< Retour à l'accueil");
    public ClubPage(ClubManager clubManager, Club club) {
        super("Club Page", clubManager);
        this.club = club;

        JPanel panel = new MainPanel();

        // Back button
        backButtonPanel.getBackButton().addActionListener(this);
        panel.add(backButtonPanel);

        // Top panel (Name of the club and "Concerts" title)
        panel.add(new TitleLabel("Club " + club.getName()));
        panel.add(new Typography("Concerts", 2));

        // Concerts list and concert creation form
        updateConcerts();
        panel.add(createConcertForm());
        panel.add(concertsPanel);

        // Adding a margin to the window
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Making the window scrollable
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

        nameField.setSize(100, 30);
        concertForm.add(nameField);

        concertForm.add(new Typography("Salle :", 3));
        Set<Room> keySet = this.clubManager.getRoomManager().getRooms().keySet();
        Room[] rooms = keySet.toArray(new Room[keySet.size()]);
        roomComboBox = new JComboBox<>(rooms);
        concertForm.add(roomComboBox);

        concertForm.add(new Typography("Prix :", 3));
        priceField.setSize(100, 30);
        concertForm.add(priceField);

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
            ConcertLine concertLine = new ConcertLine(concert, club);
            concertsPanel.add(concertLine);
            concertLine.getCancelButton().addActionListener(e -> updateConcerts());
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
        // Validation button for creating a new concert
        if(src == validationButton) {
            String newMemberName = nameField.getText();
            Room room = (Room) roomComboBox.getSelectedItem();

            String error_message = "";
            double newMemberPrice = 0;
            if(priceField.getText().isEmpty())  error_message += "Veuillez préciser un prix seuil\n";
            else {
                try {
                    newMemberPrice = Double.parseDouble(priceField.getText());
                } catch (Exception e) {
                    error_message += "Le prix seuil doit être un nombre\n";
                }
            }
            if(newMemberName.isEmpty()) error_message += "Veuillez préciser un nom";

            if(!error_message.isEmpty()) {
                showErrorMessage(error_message);
                return;
            }

            Concert concert = new Concert(newMemberName, room, newMemberPrice);
            club.addConcert(concert);
            refreshPage();
        }
    }
}
