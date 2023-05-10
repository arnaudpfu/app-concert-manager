package view.components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class DefaultPanel extends JPanel {
    public DefaultPanel(int r, int g, int b) {
        setBackground(new Color(r,g,b));
        setLayout(new GridLayout(0, 1));
    }

    public DefaultPanel() {
        this(46,46,46);
    }

    public DefaultPanel(boolean opaque) {
        this(46,46,46);
        setOpaque(opaque);
    }

    public DefaultPanel(int margin) {
        this();
        setBorder(new EmptyBorder(margin,margin,margin,margin));
    }
    public DefaultPanel(int r, int g, int b, int margin) {
        this(r,g,b);
        setBorder(new EmptyBorder(margin,margin,margin,margin));
    }

}
