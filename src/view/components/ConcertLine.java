package view.components;
import model.Concert;
import javax.swing.*;
import java.awt.*;

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
        add(Box.createRigidArea(new Dimension(50, 20)));
        add(new DefaultLabel("Salle \"" + concert.getRoom().getName() + "\""));
        add(Box.createRigidArea(new Dimension(50, 20)));
        add(new DefaultLabel(concert.getTicketPrice() + "€"));
        add(Box.createRigidArea(new Dimension(50, 20)));
        add(new DefaultLabel(concert.getPlacesRatio()));
        add(Box.createRigidArea(new Dimension(50, 20)));
        add(new DefaultLabel(concert.getDateFormat()));
        add(Box.createRigidArea(new Dimension(50, 20)));
        setOpaque(false);
        add(cancelButton);
    }

    public JButton getCancelButton() { return cancelButton; }
}
