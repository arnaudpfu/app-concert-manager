package view.components;

import javax.swing.*;
import java.awt.*;

public class PrimaryButton extends JButton {
    public PrimaryButton(String text, Integer size) {
        super(text);
        setBorderPainted(false);
        setFocusPainted(false);
        setBackground(new Color(195, 149, 252));
        setForeground(new Color(46,46,46));
        setFont(new Font("Inter", Font.PLAIN, size));
    }

    public PrimaryButton(String text) {
        this(text, 18);
    }
}
