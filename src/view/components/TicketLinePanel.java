package view.components;

import model.Ticket;

import javax.swing.*;

public class TicketLinePanel extends JPanel {
    private JButton cancelButton;
    private JLabel concertName;
    private JLabel concertState;

    public TicketLinePanel(Ticket ticket) {
        setSize(1000, 500);
        concertName.setText(ticket.getConcert().getName());
        // TODO : Update state depending on current date
        concertState.setText("En cours");
    }
}
