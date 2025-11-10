package rockpaper;

import java.awt.*;
import javax.swing.*;
import java.util.Random;

public class RPSPanel extends GradientPanel {
    private JLabel resultLabel;
    private JLabel playerChoiceLabel;
    private JLabel cpuChoiceLabel;
    private JLabel scoreLabel;
    
    private int playerWins = 0;
    private int computerWins = 0;
    private int draws = 0;

    // Image icons
    private ImageIcon rockIcon, paperIcon, scissorIcon;

    public RPSPanel(Main frame) {
        super(new Color(45, 15, 80), new Color(110, 70, 200));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Load images from classpath
        try {
            ImageIcon originalRock = new ImageIcon(getClass().getResource("/rockpaper/images/rock.png"));
            rockIcon = new ImageIcon(originalRock.getImage().getScaledInstance(160, 160, Image.SCALE_SMOOTH));
            
            ImageIcon originalPaper = new ImageIcon(getClass().getResource("/rockpaper/images/paper.png"));
            paperIcon = new ImageIcon(originalPaper.getImage().getScaledInstance(160, 160, Image.SCALE_SMOOTH));
            
            ImageIcon originalScissor = new ImageIcon(getClass().getResource("/rockpaper/images/scissors.png"));
            scissorIcon = new ImageIcon(originalScissor.getImage().getScaledInstance(160, 160, Image.SCALE_SMOOTH));
        } catch (Exception e) {
            System.err.println("Error loading images: " + e.getMessage());
            rockIcon = new ImageIcon();
            paperIcon = new ImageIcon();
            scissorIcon = new ImageIcon();
        }

        // Top panel with back button and title
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.setOpaque(false);
        topPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
        topPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        // Back button on the left
        RoundedButton backBtn = new RoundedButton("← BACK");
        backBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        backBtn.setPreferredSize(new Dimension(100, 40));
        backBtn.addActionListener(e -> frame.showCard(Main.CARD_MODE));

        // Title in center
        JLabel title = new JLabel("ROCK PAPER SCISSORS", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 32));
        title.setForeground(Color.WHITE);

        topPanel.add(backBtn, BorderLayout.WEST);
        topPanel.add(title, BorderLayout.CENTER);

        add(topPanel);

        // Info panel (You and Computer choices)
        JPanel infoPanel = new JPanel(new BorderLayout());
        infoPanel.setOpaque(true);
        infoPanel.setBackground(new Color(255, 255, 255, 250));
        infoPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(30, 30, 30), 3),
            BorderFactory.createEmptyBorder(18, 25, 18, 25)
        ));
        infoPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));

        // Player info (left side)
        playerChoiceLabel = new JLabel("YOU:  ");
        playerChoiceLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        playerChoiceLabel.setForeground(new Color(30, 30, 30));

        // Computer info (right side)
        cpuChoiceLabel = new JLabel("  COMPUTER");
        cpuChoiceLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        cpuChoiceLabel.setForeground(new Color(30, 30, 30));

        infoPanel.add(playerChoiceLabel, BorderLayout.WEST);
        infoPanel.add(cpuChoiceLabel, BorderLayout.EAST);

        JPanel infoPanelWrapper = new JPanel();
        infoPanelWrapper.setLayout(new BorderLayout());
        infoPanelWrapper.setOpaque(false);
        infoPanelWrapper.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        infoPanelWrapper.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));
        infoPanelWrapper.add(infoPanel, BorderLayout.CENTER);

        add(infoPanelWrapper);

        // Buttons panel - horizontal layout
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 45, 0));
        buttonsPanel.setOpaque(false);
        buttonsPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 250));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(30, 20, 20, 20));

        JButton rockBtn = createStyledButton(rockIcon, "ROCK");
        JButton paperBtn = createStyledButton(paperIcon, "PAPER");
        JButton scissorBtn = createStyledButton(scissorIcon, "SCISSORS");

        rockBtn.addActionListener(e -> play("ROCK"));
        paperBtn.addActionListener(e -> play("PAPER"));
        scissorBtn.addActionListener(e -> play("SCISSORS"));

        buttonsPanel.add(rockBtn);
        buttonsPanel.add(paperBtn);
        buttonsPanel.add(scissorBtn);

        add(buttonsPanel);

        // Result panel directly under buttons
        JPanel resultPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        resultPanel.setOpaque(false);
        resultPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        resultPanel.setBorder(BorderFactory.createEmptyBorder(10, 50, 30, 50));

        resultLabel = new JLabel("Choose your move!", SwingConstants.CENTER);
        resultLabel.setFont(new Font("Segoe UI", Font.BOLD, 42));
        resultLabel.setForeground(Color.WHITE);

        resultPanel.add(resultLabel);
        add(resultPanel);

        // Add glue to push everything up
        add(Box.createVerticalGlue());
    }

    private JButton createStyledButton(ImageIcon icon, String name) {
        JButton btn = new JButton(icon);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setOpaque(false);
        btn.setPreferredSize(new Dimension(180, 180));
        
        // Add hover effect
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBorderPainted(true);
                btn.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(255, 255, 255, 200), 4),
                    BorderFactory.createEmptyBorder(2, 2, 2, 2)
                ));
            }
            
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBorderPainted(false);
            }
        });
        
        return btn;
    }

    private void play(String playerChoice) {
        String[] choices = {"ROCK", "PAPER", "SCISSORS"};
        String cpuChoice = choices[new Random().nextInt(3)];

        playerChoiceLabel.setText("YOU:  " + playerChoice);
        cpuChoiceLabel.setText(cpuChoice + "  COMPUTER");

        String result;
        if (playerChoice.equals(cpuChoice)) {
            result = "DRAW!!!";
            resultLabel.setForeground(new Color(255, 215, 0)); // Gold color
        } else if ((playerChoice.equals("ROCK") && cpuChoice.equals("SCISSORS")) ||
                   (playerChoice.equals("PAPER") && cpuChoice.equals("ROCK")) ||
                   (playerChoice.equals("SCISSORS") && cpuChoice.equals("PAPER"))) {
            result = "YOU: WIN!!!";
            
            resultLabel.setForeground(new Color(50, 205, 50)); // Lime green
        } else {
            result = "YOU: LOSE!!!";
            resultLabel.setForeground(new Color(255, 69, 58)); // Modern red
        }

        resultLabel.setText(result);
    }
}