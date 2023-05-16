package view.components;

import javax.swing.*;
import java.awt.*;

public class DefaultLabel extends JLabel {
    public DefaultLabel(String text, Boolean isDark) {
        super(text);
        if(!isDark) setForeground(new Color(229, 229, 229));
        setFont(new Font("Inter", Font.PLAIN, 18));
        setOpaque(false);
    }

    public DefaultLabel(String text, Color color) {
        this(text);
        setForeground(color);
    }

    public DefaultLabel(String text) { this(text, false); }
}
