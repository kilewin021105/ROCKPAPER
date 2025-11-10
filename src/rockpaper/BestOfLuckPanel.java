package rockpaper;

import java.awt.*;
import javax.swing.*;

public class BestOfLuckPanel extends GradientPanel {
    private JLabel modeLabel;

    public BestOfLuckPanel(Main frame) {
        super(new Color(35, 0, 60), new Color(140, 60, 220));
        setLayout(new BorderLayout());

        JLabel title = new JLabel("BEST OF LUCK!", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 48));
        title.setForeground(Color.white);
        add(title, BorderLayout.NORTH);

        modeLabel = new JLabel("", SwingConstants.CENTER);
        modeLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        modeLabel.setForeground(new Color(240, 220, 255));
        add(modeLabel, BorderLayout.CENTER);

        RoundedButton backBtn = new RoundedButton("Back to Menu");
        backBtn.addActionListener(e -> frame.showCard(Main.CARD_MENU));
        JPanel bottom = new JPanel();
        bottom.setOpaque(false);
        bottom.add(backBtn);
        add(bottom, BorderLayout.SOUTH);
    }

    public void setModeText(String text) {
        modeLabel.setText(text);
    }
}
