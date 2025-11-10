package rockpaper;

import java.awt.*;
import javax.swing.*;

public class MenuPanel extends GradientPanel {
    private Main frame;

    public MenuPanel(Main frame) {
        super(new Color(40, 10, 70), new Color(110, 70, 200));
        this.frame = frame;

        // Use GridBagLayout to perfectly center content
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        // Inner panel to stack title + button vertically
        JPanel innerPanel = new JPanel();
        innerPanel.setOpaque(false);
        innerPanel.setLayout(new BoxLayout(innerPanel, BoxLayout.Y_AXIS));

        // Title
        JLabel title = new JLabel("PLAY NOW", SwingConstants.CENTER);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setForeground(Color.white);
        title.setFont(new Font("Segoe UI", Font.BOLD, 60));

        // Add some spacing between title and button
        innerPanel.add(title);
        innerPanel.add(Box.createVerticalStrut(25));

        // Button
        RoundedButton playBtn = new RoundedButton("PLAY NOW");
        playBtn.setPreferredSize(new Dimension(200, 50));
        playBtn.setFont(new Font("Segoe UI", Font.BOLD, 18));
        playBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        playBtn.addActionListener(e -> frame.showCard(Main.CARD_MODE));

        innerPanel.add(playBtn);

        // Add inner panel to the center of the screen
        add(innerPanel, gbc);
    }
}
