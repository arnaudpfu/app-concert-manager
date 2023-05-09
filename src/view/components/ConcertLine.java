package view.components;
import model.Concert;
import javax.swing.*;

public class ConcertLine extends JPanel {
    private JButton cancelButton = new JButton("Annuler");
    private Concert concert;

    /**
     * A concert line if often used in a JPanel to represent a concert (name, available places, number of places, price)
     * @param _concert The concert
     */
    public ConcertLine(Concert _concert) {
        this.concert = _concert;

        add(new Typography(concert.getName(), 3));
        add(new Typography(
                concert.getNbMaxPlaces() - concert.getNbFreePlaces() + "/"
                        + concert.getNbMaxPlaces(),
                3));
        add(new Typography(concert.getTicketPrice() + "€", 3));
        add(cancelButton);
    }

    public JButton getCancelButton() { return cancelButton; }
}
