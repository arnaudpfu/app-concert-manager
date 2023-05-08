package view.components;

import model.Club;
import model.Concert;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ConcertLine extends JPanel implements ActionListener {
    private JButton cancelButton = new JButton("Annuler");
    private Club club;
    private Concert concert;

    /**
     * A concert line if often used in a JPanel to represent a concert (name, available places, number of places, price)
     * @param _concert The concert
     * @param _club The club that created the concert
     */
    public ConcertLine(Concert _concert, Club _club) {
        this.club = _club;
        this.concert = _concert;

        add(new Typography(concert.getName(), 3));
        add(new Typography(
                concert.getNbMaxPlaces() - concert.getNbFreePlaces() + "/"
                        + concert.getNbMaxPlaces(),
                3));
        add(new Typography(concert.getTicketPrice() + "€", 3));
        cancelButton.addActionListener(this);
        add(cancelButton);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if(event.getSource() == cancelButton) {
            club.removeConcert(concert);
        }
    }

    public JButton getCancelButton() { return cancelButton; }
}
