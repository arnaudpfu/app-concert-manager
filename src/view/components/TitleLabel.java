package view.components;

import java.awt.*;

public class TitleLabel extends DefaultLabel {
    public TitleLabel(String text) {
        super(text);
        setFont(new Font("Inter", Font.BOLD, 36));
    }
}
