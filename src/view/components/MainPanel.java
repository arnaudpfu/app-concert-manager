package view.components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MainPanel extends JPanel {
    public MainPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(41, 41, 41));
        // Adding margins
        setBorder(new EmptyBorder(20, 50, 50, 50));
        setForeground(new Color(187, 187, 187));
    }
}
