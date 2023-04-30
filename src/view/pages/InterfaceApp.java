package view.pages;

import java.io.IOException;
import java.io.InputStream;
import java.io.File;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.ClubManager;

import javax.swing.BoxLayout;

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

        this.registerFonts();

        // center the frame on the screen
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public void registerFonts() {
        Font roboto = null;
        try {
            InputStream is = InterfaceApp.class.getResourceAsStream("..//fonts//Roboto-Regular.ttf");
            roboto = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(16f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(roboto);
        } catch (IOException | FontFormatException e) {
            System.out.println("Echec de l'enregistrement de la police.");
            e.printStackTrace();
        }
    }

}
