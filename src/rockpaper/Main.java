package rockpaper;

import java.awt.*;
import javax.swing.*;

public class Main extends JFrame {
    public static final String CARD_MENU = "menu";
    public static final String CARD_MODE = "mode";
    public static final String CARD_BEST = "best";
    public static final String CARD_RPS = "rps";


    private CardLayout cardLayout;
    private JPanel cards;

    public MenuPanel menuPanel;
    public ModeSelectPanel modePanel;
    public BestOfLuckPanel bestPanel;
    public RPSPanel rpsPanel;
    

    public Main() {
        setTitle("CP5 Launcher");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
        setResizable(false);

        cardLayout = new CardLayout();
        cards = new JPanel(cardLayout);

        // Create panels
        menuPanel = new MenuPanel(this);
        modePanel = new ModeSelectPanel(this);
        bestPanel = new BestOfLuckPanel(this);
        rpsPanel = new RPSPanel(this);
        
        cards.add(rpsPanel, CARD_RPS);
        cards.add(menuPanel, CARD_MENU);
        cards.add(modePanel, CARD_MODE);
        cards.add(bestPanel, CARD_BEST);

        add(cards);
        cardLayout.show(cards, CARD_MENU);
    }

    public void showCard(String cardName) {
        cardLayout.show(cards, cardName);
    }

    public void showBestOfLuck(String mode) {
    bestPanel.setModeText(mode);
    showCard(CARD_BEST);
    if (mode.equals("Quick Match")) {
        // go directly to RPS game instead of best panel
        showCard(CARD_RPS);
    }
}


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Main().setVisible(true);
        });
    }
}
