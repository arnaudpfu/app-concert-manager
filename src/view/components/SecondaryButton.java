package view.components;

import javax.swing.*;
import java.awt.*;

public class SecondaryButton extends JButton {
    public SecondaryButton(String text, Integer size) {
        super(text);
        setBorderPainted(false);
        setFocusPainted(false);
        setOpaque(false);
        setForeground(new Color(195, 149, 252));
        setBackground(new Color(46,46,46));
        setFont(new Font("Inter", Font.PLAIN, size));
    }

    public SecondaryButton(String text) {
        this(text, 18);
    }
}
