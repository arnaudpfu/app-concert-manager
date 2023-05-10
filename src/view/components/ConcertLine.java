package view.components;
import model.Concert;
import javax.swing.*;

public class ConcertLine extends JPanel {
    private JButton cancelButton = new SecondaryButton("Annuler");
    private Concert concert;

    /**
     * A concert line if often used in a JPanel to represent a concert (name, available places, number of places, price)
     * @param _concert The concert
     */
    public ConcertLine(Concert _concert) {
        this.concert = _concert;

        add(new DefaultLabel(concert.getName()));
        add(new DefaultLabel(concert.getTicketPrice() + "€"));
        add(new DefaultLabel(concert.getRoom().getPlacesRatio()));
        setOpaque(false);
        add(cancelButton);
    }

    public JButton getCancelButton() { return cancelButton; }
}
