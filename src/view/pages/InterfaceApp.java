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
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    protected void addSpacer(JPanel panel) {
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
    }

    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
