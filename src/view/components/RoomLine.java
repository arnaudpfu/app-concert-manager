package view.components;

import model.Room;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RoomLine extends JPanel {
    public RoomLine(Room room) {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setOpaque(false);

        add(new DefaultLabel(room.getName(), new Color(195, 149, 252)));

        if(room.hasNoReservation()){
            add(Box.createRigidArea(new Dimension(100, 20)));
            add(new DefaultLabel("Pas de concert prévu"));
        }

        for (Date date : room.getDates()) {
            add(Box.createRigidArea(new Dimension(50, 20)));
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String dateString = dateFormat.format(date);
            add(new DefaultLabel(dateString));
            add(Box.createRigidArea(new Dimension(10, 20)));
            add(new DefaultLabel(room.getPlacesRatio(date)));
        }
    }
}
