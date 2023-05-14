package view.components;

import model.Room;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class RoomLine extends JPanel {
    public RoomLine(Room room) {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setOpaque(false);

        add(new DefaultLabel(room.getName()));
        add(Box.createRigidArea(new Dimension(100, 20)));

        if(room.getBookedDates().isEmpty()){ add(new DefaultLabel("Pas de concert prévu")); }

        for (Date date : room.getBookedDates().keySet()) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String dateString = dateFormat.format(date);
            add(new DefaultLabel(dateString));
            add(Box.createRigidArea(new Dimension(100, 20)));
            add(new DefaultLabel(room.getPlacesRatio(date)));
        }
    }
}
