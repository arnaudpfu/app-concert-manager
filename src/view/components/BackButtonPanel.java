package view.components;

import javax.swing.*;
import java.awt.*;

public class BackButtonPanel extends JPanel {
    private JButton backButton;

    public BackButtonPanel(String text) {
        super(new FlowLayout(FlowLayout.LEFT));
        backButton = new JButton(text);
        backButton.setFocusPainted(false);
        backButton.setBorderPainted(false);
        backButton.setForeground(new Color(171,171,171));
        backButton.setBackground(new Color(41,41,41));
        backButton.setFont(new Font("Inter", Font.PLAIN, 18));
        add(backButton);
        setOpaque(false);
    }

    public JButton getBackButton() { return backButton; }
}
