package view.components;

import javax.swing.*;
import java.awt.*;

public class SecondaryButton extends BoxRadius {
    public SecondaryButton(String text, Integer size) {
        super(new Color(67,67,67));
        setOpaque(false);
        JButton button = new JButton(text);
        button.setOpaque(false);
        button.setForeground(new Color(195, 149, 252));
        button.setFont(new Font("Inter", Font.PLAIN, size));
        button.setOpaque(false);
        add(button);
    }

    public SecondaryButton(String text) {
        this(text, 18);
    }
}
