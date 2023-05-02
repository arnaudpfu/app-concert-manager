package view.pages;

import java.io.IOException;
import java.io.File;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.ClubManager;

abstract public class InterfaceApp extends JFrame {

    /**
     * Id pour la serialisation
     */
    private static final long serialVersionUID = 1L;

    /**
     * Largeur de la fenêtre
     */
    private static final int WIDTH = 800;
    /**
     * Hauteur de la fenêtre
     */
    private static final int HEIGHT = 800;

    /**
     * ClubManager
     */
    protected ClubManager clubManager;

    ImageIcon logo = new ImageIcon(".//src//images//logo.png");

    public InterfaceApp(String title, ClubManager clubManager) {
        this.clubManager = clubManager;

        setTitle(title);
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setIconImage(logo.getImage());
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // this.registerFonts();

        // center the frame on the screen
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    protected void addSpacer(JPanel panel) {
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
    }

    public void registerFonts() {
        try {
            File fontFile = new File("src\\fonts\\Roboto-Regular.ttf");
            // InputStream is =
            // getClass().getClassLoader().getResourceAsStream("src\\fonts\\Roboto-Regular.ttf");
            Font roboto = Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(16f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(roboto);
        } catch (IOException | FontFormatException e) {
            System.out.println("Echec de l'enregistrement de la police.");
            e.printStackTrace();
        }
    }

}
