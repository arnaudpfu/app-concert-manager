package view.components;

import javax.swing.*;
import java.awt.*;

public class DefaultTextField extends JTextField {
    public DefaultTextField() {
        setPreferredSize(new Dimension(200, -1));
        setAutoscrolls(true);
        setBackground(new Color(46,46,46));
        setForeground(new Color(229,229,229));
        setCaretColor(new Color(229,229,229));
        setHorizontalAlignment(LEFT);
        setSelectionColor(new Color(187,134,252));
        setOpaque(false);
        setFont(new Font("Inter", Font.PLAIN, 18));
    }
}
