package view.pages;

import javax.swing.*;

import model.ClubManager;

import java.awt.*;
import java.awt.event.*;

public class ConnexionPage extends InterfaceApp {
    private JLabel title;
    private JLabel nameLabel;
    private JTextField nameField;
    private JButton submitButton;
    private JButton backButton;
    private String userType;
    // private Controller controller;

    public ConnexionPage(ClubManager clubManager, String userType) {
        super("Connexion", clubManager);
        // this.controller = controller;
        this.userType = userType;
        this.setTitle("Concert Manager - Connexion");

        JPanel panel = new JPanel(new GridLayout(4, 1));

        backButton = new JButton("< Back to Home");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // navigate back to home page
                HomePage homePage = new HomePage(clubManager);
                homePage.setVisible(true);
                dispose();
            }
        });

        title = new JLabel("Connexion");
        title.setFont(new Font("Serif", Font.BOLD, 24));
        title.setHorizontalAlignment(JLabel.CENTER);

        nameLabel = new JLabel("Name:");
        nameLabel.setFont(new Font("Serif", Font.PLAIN, 18));
        nameLabel.setHorizontalAlignment(JLabel.CENTER);

        nameField = new JTextField();
        nameField.setFont(new Font("Serif", Font.PLAIN, 18));
        nameField.setHorizontalAlignment(JTextField.CENTER);

        submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Serif", Font.PLAIN, 18));
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                // TODO: validate name
                // TODO: determine if the user is a member or a club and navigate to the
                // appropriate page based on userType
                if (userType.equals("member")) {
                    // controller.showMemberPage();
                } else if (userType.equals("club")) {
                    // controller.showClubPage();
                }
                dispose();
            }
        });

        panel.add(backButton);
        panel.add(title);
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(submitButton);

        this.add(panel);
        this.setLocationRelativeTo(null);
    }

    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
