package view.pages;

import java.io.IOException;
import java.io.File;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.Serial;

import javax.swing.*;

import model.ClubManager;

abstract public class InterfaceApp extends JFrame {

    /**
     * Id pour la serialisation
     */
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Largeur de la fenêtre
     */
    protected static final int WIDTH = 800;
    /**
     * Hauteur de la fenêtre
     */
    protected static final int HEIGHT = 600;
    /**
     * Largeur intérieure de la fenêtre
     */
    protected static final int INNER_WIDTH = 700;
    /**
     * Hauteur intérieure de la fenêtre
     */
    protected static final int INNER_HEIGHT = 700;

    /**
     * ClubManager
     */
    protected ClubManager clubManager;

    ImageIcon logo = new ImageIcon(".//src//images//logo.png");

    public InterfaceApp(String title, ClubManager clubManager) {
        this.clubManager = clubManager;

        setTitle(title);
        setSize(WIDTH, HEIGHT);
        setIconImage(logo.getImage());
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // this.registerFonts();

        // center the frame on the screen
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public void registerFonts() {
        try {
            File fontFile = new File("src\\fonts\\Roboto-Regular.ttf");
            Font roboto = Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(16f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(roboto);
        } catch (IOException | FontFormatException e) {
            System.out.println("Echec de l'enregistrement de la police.");
            e.printStackTrace();
        }
    }

    protected void addSpacer(JPanel panel) {
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
    }

    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
