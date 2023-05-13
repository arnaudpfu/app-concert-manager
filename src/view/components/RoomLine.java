package view.components;

import model.Room;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class RoomLine extends JPanel {
    public RoomLine(Room room, ArrayList<Date> dates) {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setOpaque(false);

        add(new DefaultLabel(room.getName()));
        add(Box.createRigidArea(new Dimension(100, 20)));

        for (Date date : dates) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String dateString = dateFormat.format(date);
            add(new DefaultLabel(dateString));
        }

        if(!dates.isEmpty()){
            add(Box.createRigidArea(new Dimension(100, 20)));
            add(new DefaultLabel(room.getPlacesRatio()));
        }
    }
}
