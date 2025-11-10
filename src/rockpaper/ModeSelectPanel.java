package rockpaper;

import java.awt.*;
import javax.swing.*;

public class ModeSelectPanel extends GradientPanel {
    private Main frame;

    public ModeSelectPanel(Main frame) {
        super(new Color(50, 20, 90), new Color(120, 60, 210));
        this.frame = frame;
        setLayout(new BorderLayout());

        JLabel header = new JLabel("Choose Your Mode", SwingConstants.CENTER);
        header.setFont(new Font("Segoe UI", Font.BOLD, 40));
        header.setForeground(Color.white);
        add(header, BorderLayout.NORTH);

        JPanel btnPanel = new JPanel(new GridLayout(1, 2, 15, 15));
        btnPanel.setOpaque(false);

        RoundedButton quick = new RoundedButton("Quick Match");
        quick.addActionListener(e -> frame.showBestOfLuck("Quick Match"));
        RoundedButton classic = new RoundedButton("Classic Showdown");
        classic.addActionListener(e -> frame.showBestOfLuck("Classic Showdown"));
        RoundedButton endless = new RoundedButton("Endless Fun");
        endless.addActionListener(e -> frame.showBestOfLuck("Endless Fun"));

        btnPanel.add(quick);
        btnPanel.add(classic);
        btnPanel.add(endless);

        add(btnPanel, BorderLayout.CENTER);

        RoundedButton back = new RoundedButton("← Back");
        back.addActionListener(e -> frame.showCard(Main.CARD_MENU));
        JPanel bottom = new JPanel();
        bottom.setOpaque(false);
        bottom.add(back);
        add(bottom, BorderLayout.SOUTH);
    }
}
