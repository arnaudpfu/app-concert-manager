package view.components;

import javax.swing.*;
import java.awt.*;

public class Typography extends JLabel {

    public Typography(String content, int level) {
        super(content);

        switch (level) {
            case 1:
                this.setFont(new Font("Arial", Font.PLAIN, 40));
                break;
            case 2:
                this.setFont(new Font("Arial", Font.PLAIN, 30));
                break;
            case 3:
                this.setFont(new Font("Arial", Font.PLAIN, 20));
                break;
            default:
                this.setFont(new Font("Arial", Font.PLAIN, 15));
                break;
        }
    }
}
