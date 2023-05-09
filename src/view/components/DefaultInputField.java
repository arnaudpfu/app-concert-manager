package view.components;

import javax.swing.*;

public class DefaultInputField extends JPanel {
    public DefaultInputField(JLabel title, JComponent input) {
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        setOpaque(false);
        add(title);
        add(Box.createHorizontalStrut(100));
        add(input);
    }
}
