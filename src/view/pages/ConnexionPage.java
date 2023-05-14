package view.pages;

import model.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public abstract class ConnexionPage extends InterfaceApp implements ActionListener {
    private JPanel mainPanel;
    private JPanel connexionPanel;
    protected JLabel title;
    private JLabel nameLabel;
    protected JTextField nameInput;
    protected JButton connectButton;
    protected JLabel errorLabel;
    protected JButton backButton;

    public ConnexionPage(ArrayList<Club> clubs, ArrayList<Member> members, RoomManager roomManager) {
        super("Concert Manager - Connexion", clubs, members, roomManager);
        setContentPane(mainPanel);
        backButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent event) {
        Object src = event.getSource();
        if(src == backButton) {
            new HomePage(clubs, members, roomManager);
            dispose();
        }
    }
}
