package view.components;

import model.Ticket;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class TicketLinePanel extends DefaultTableCellRenderer {
    private Ticket ticket;
    public TicketLinePanel(Ticket ticket) {
        this.ticket = ticket;
    }
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        JPanel ticketPanel = new JPanel();
        ticketPanel.setOpaque(false);
        ticketPanel.add(new DefaultLabel(ticket.getConcert().getName()));
        ticketPanel.add(new SecondaryButton("Annuler"));
        ticketPanel.add(new DefaultLabel("En cours"));
        return ticketPanel;
    }
}
