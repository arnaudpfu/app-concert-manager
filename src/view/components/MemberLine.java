package view.components;

import model.Member;

import javax.swing.*;
import java.awt.*;

public class MemberLine extends JPanel {
    private Member member;
    private JButton removeButton = new SecondaryButton("Retirer");
    public MemberLine(Member _member) {
        this.member = _member;
        setOpaque(false);
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        JLabel nameLabel = new DefaultLabel(member.getNameFormat());
        nameLabel.setPreferredSize(new Dimension(100, -1));
        add(nameLabel);
        add(new DefaultLabel(member.getPriceThreshold() + " €"));
        add(Box.createHorizontalGlue());
        add(removeButton);
    }

    public JButton getRemoveButton() { return removeButton; }
}
