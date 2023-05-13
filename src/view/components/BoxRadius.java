package view.components;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class BoxRadius extends JPanel {
    public Color color;

    public BoxRadius(Color color) {
        this.color = color;
        this.setOpaque(false);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Set the border color and thickness
        g2.setColor(this.color);
        g2.setStroke(new BasicStroke(1));

        // Set the border radius
        int borderRadius = 20;
        int borderWidth = 1;
        int width = getWidth() - 2 * borderWidth;
        int height = getHeight() - 2 * borderWidth;
        RoundRectangle2D border = new RoundRectangle2D.Double(borderWidth, borderWidth, width, height, borderRadius,
                borderRadius);

        // Draw the box with border
        g2.draw(border);
    }
}
