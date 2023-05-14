package view.components;

import java.awt.*;

public class DefaultErrorLabel extends DefaultLabel {
    public DefaultErrorLabel(String text) {
        super(text);
        setForeground(new Color(207,102,121));
        setFont(new Font("Inter", Font.BOLD, getFont().getSize()));
    }
    public DefaultErrorLabel() { this(""); }
}
