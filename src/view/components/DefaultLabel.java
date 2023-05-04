package view.components;

import javax.swing.*;
import java.awt.*;

public class DefaultLabel extends JLabel {
    public DefaultLabel(String text) {
        super(text);
        setForeground(new Color(229, 229, 229));
        setFont(new Font("Inter", Font.PLAIN, 18));
        setOpaque(false);
    }
}
