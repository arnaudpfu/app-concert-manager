package view.pages;
import java.io.Serial;

import javax.swing.*;

import model.ClubManager;
import view.components.MainPanel;

abstract public class InterfaceApp extends JFrame {
    @Serial
    private static final long serialVersionUID = 1L;
    protected static final int WIDTH = 800;
    protected static final int HEIGHT = 600;
    protected static final int INNER_WIDTH = 700;
    protected static final int INNER_HEIGHT = 700;
    protected ClubManager clubManager;
    ImageIcon logo = new ImageIcon(".//src//images//logo.png");
    protected JPanel mainPanel;

    public InterfaceApp(String title, ClubManager clubManager) {
        this.clubManager = clubManager;
        this.mainPanel = new MainPanel();

        setTitle(title);
        setSize(WIDTH, HEIGHT);
        setIconImage(logo.getImage());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    /**
     * Method that can be called by child classes when they finish adding component to the window
     * For now, it only makes the window scrollable.
     * (To make the window not scrollable, use setContentPane(mainPanel) instead).
     */
    protected void endFrameCreation() {
        // Makes the mainPanel scrollable
        JScrollPane scrollPanel = new JScrollPane(mainPanel);
        scrollPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPanel.getVerticalScrollBar().setUnitIncrement(50);
        scrollPanel.getHorizontalScrollBar().setUnitIncrement(50);
        setContentPane(scrollPanel);
    }

    protected void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
