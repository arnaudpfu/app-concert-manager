package view.pages;

import model.Club;
import model.Member;
import model.RoomManager;
import model.exceptions.UnknownMemberException;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Objects;

public class ConnexionPageMember extends ConnexionPage {
    private ArrayList<Member> members;
    public ConnexionPageMember(ArrayList<Club> clubs, ArrayList<Member> members, RoomManager roomManager) {
        super(clubs, members, roomManager);
        this.members = members;
        title.setText("Connexion - Membre");
        connectButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        super.actionPerformed(event);
        Object src = event.getSource();
        if(src == connectButton) {
            String textInput = nameInput.getText().toLowerCase();

            // Validates input
            if (textInput.isEmpty()) {
                errorLabel.setText("Veuillez insérer un nom de membre");
                return;
            }

            // Check if user exists
            try {
                Member member = getMember(textInput);
                new MemberPage(clubs, members, roomManager, member);
                dispose();
            } catch (UnknownMemberException ex) {
                errorLabel.setText("Ce membre n'existe pas");
            }
        }
    }

    public Member getMember(String name) {
        for (Member member : members) {
            if(Objects.equals(member.getName(), name)) return member;
        }
        throw new UnknownMemberException(name);
    }
}
